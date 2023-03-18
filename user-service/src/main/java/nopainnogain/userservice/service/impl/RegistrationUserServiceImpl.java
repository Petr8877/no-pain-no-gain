package nopainnogain.userservice.service.impl;

import nopainnogain.userservice.core.dto.user.*;
import nopainnogain.userservice.core.exception.SingleErrorResponse;
import nopainnogain.userservice.entity.Role;
import nopainnogain.userservice.entity.User;
import nopainnogain.userservice.repository.RegistrationUserRepository;
import nopainnogain.userservice.service.api.IRegistrationUserService;
import nopainnogain.userservice.entity.Status;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.UUID;


@Service
public class RegistrationUserServiceImpl implements IRegistrationUserService {

    private final PasswordEncoder encoder;
    private final ConversionService conversionService;
    private final RegistrationUserRepository repository;
        private static final String URL = "http://mail-service:8080/mail";
//    private static final String URL = "http://localhost:8091/mail";


    public RegistrationUserServiceImpl(PasswordEncoder encoder, ConversionService conversionService, RegistrationUserRepository repo) {
        this.encoder = encoder;
        this.conversionService = conversionService;
        this.repository = repo;
    }

    @Override
    public User registrationUser(RegistrationDto registrationDTO) {
        if (!repository.existsByEmail(registrationDTO.email())) {
            User user = conversionService.convert(registrationDTO, User.class);
            User save = repository.save(user);
            toAudit("Регистрация нового пользователя", save.getUuid(), save.getEmail(), save.getFio(), save.getRole());
            try {
                send(save.getEmail(), createLink(save.getEmail(), save.getUuid())); //todo добавить многопоточку
            } catch (IOException e) {
                throw new SingleErrorResponse("Отправка письма не удалась");
            }
            return save;
        } else {
            throw new SingleErrorResponse("Данная почта уже была использована для регистрации");
        }

    }

    @Override
    public boolean verification(String code, String email) {
        UUID uuid = UUID.fromString(code);
        User user = repository.findById(uuid).orElseThrow(()
                -> new SingleErrorResponse("Такого пользователя нет в базе"));
        if (Objects.equals(user.getEmail(), email)) {
            toAudit("Верификация пользователя", user.getUuid(), email, user.getFio(), user.getRole());
            switch (user.getStatus()) {
                case WAITING_ACTIVATION -> {
                    user.setStatus(Status.ACTIVATED);
                    repository.save(user);
                    return true;
                }
                case DEACTIVATED -> throw new SingleErrorResponse("Пользователь деактивирован");
                case ACTIVATED -> throw new SingleErrorResponse("Данный пользователь уже был активирован");
            }
        } else {
            throw new SingleErrorResponse("Указан не корректный адрес электронной почты");
        }
        return false;
    }

    @Override
    public DetailsDto login(LoginDto loginDto) {
        User userByEmail = repository.findByEmail(loginDto.email()).orElseThrow(()
                -> new UsernameNotFoundException("User nit found"));
        DetailsDto user = conversionService.convert(userByEmail, DetailsDto.class);
        if (!encoder.matches(loginDto.password(), Objects.requireNonNull(user).getPassword())) {
            throw new IllegalArgumentException("Пароль неверный");
        }
        toAudit("Вход в систему", userByEmail.getUuid(),user.getUsername(), userByEmail.getFio(), userByEmail.getRole());
        return user;
    }

    @Override
    public SaveUserDto aboutMe() {
        User user = repository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new UsernameNotFoundException("User nit found"));
        toAudit("Получение информации о себе");
        return conversionService.convert(user, SaveUserDto.class);
    }

    private String createLink(String email, UUID uuid) {
        return "http://localhost:80/api/v1/users/verification?code="
                + uuid + "&email=" + email;
    }

    private static void send(String to, String text) throws IOException {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(URL);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                          "to": "%s",
                          "subject": "Регистрация прошла успешно!",
                          "text": "%s"
                        }
                        """.formatted(to, text)))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void toAudit(String text) {
        DetailsDto principal = (DetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            SendToAuditService.sendAudit(principal.getUuid(), principal.getUsername(), principal.getFio(), principal.getRole(), text);
        } catch (IOException e) {
            throw new SingleErrorResponse("Запись в аудит не удалась");
        }
    }

    private void toAudit(String text, UUID uuid, String email, String fio, Role role) {
        try {
            SendToAuditService.sendAudit(uuid, email, fio, role, text);
        } catch (IOException e) {
            throw new SingleErrorResponse("Запись в аудит не удалась");
        }
    }
}
