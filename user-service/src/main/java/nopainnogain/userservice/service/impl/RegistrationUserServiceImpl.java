package nopainnogain.userservice.service.impl;

import nopainnogain.userservice.core.dto.user.DetailsDto;
import nopainnogain.userservice.core.dto.user.LoginDto;
import nopainnogain.userservice.core.dto.user.SaveUserDto;
import nopainnogain.userservice.core.dto.user.UserRegistrationDto;
import nopainnogain.userservice.core.exception.SingleErrorResponse;
import nopainnogain.userservice.entity.User;
import nopainnogain.userservice.repository.RegistrationUserRepository;
import nopainnogain.userservice.service.api.IRegistrationUserService;
import nopainnogain.userservice.entity.Status;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.service.annotation.GetExchange;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Service
public class RegistrationUserServiceImpl implements IRegistrationUserService{

    private final PasswordEncoder encoder;
    private final ConversionService conversionService;

    private final RegistrationUserRepository repo;
    private static final String URL = "http://mail-service:8080/api/v1/mail";


    public RegistrationUserServiceImpl(PasswordEncoder encoder, ConversionService conversionService, RegistrationUserRepository repo) {
        this.encoder = encoder;
        this.conversionService = conversionService;
        this.repo = repo;
    }

    @Override
    public void registrationUser(UserRegistrationDto userRegistrationDTO) {
        if (!repo.existsByEmail(userRegistrationDTO.email())) {
        User save = repo.save(Objects.requireNonNull(conversionService.convert(userRegistrationDTO, User.class)));
        try {
            sendGET(save.getEmail(), createLink(save.getEmail(), save.getUuid()));
        } catch (IOException e) {
            throw new RuntimeException(e); //todo
        }
        } else {
           throw  new SingleErrorResponse("Данная почта уже была использована для регистрации");
        }
    }

    @GetExchange
    @Override
    public void verification(String code, String email) {
        User user = repo.findById(UUID.fromString(code)).orElseThrow(()
                -> new SingleErrorResponse("Такого пользователя нет в базе"));
        if (Objects.equals(user.getEmail(), email)) {
            switch (user.getStatus()) {
                case WAITING_ACTIVATION -> {
                    user.setStatus(Status.ACTIVATED);
                    user.setDtUpdate(LocalDateTime.now());
                    repo.save(user);
                }
                case DEACTIVATED -> throw new SingleErrorResponse("Пользователь деактивирован");
                case ACTIVATED -> throw new SingleErrorResponse("Данный пользователь уже был активирован");
            }
        } else {
            throw new SingleErrorResponse("Указан не корректный адрес электронной почты");
        }
    }

    @Override
    public DetailsDto login(LoginDto loginDto) {
        DetailsDto user = conversionService.convert(repo.findByEmail(loginDto.email()), DetailsDto.class);//todo
        if(!encoder.matches(loginDto.password(), Objects.requireNonNull(user).getPassword())){
            throw new IllegalArgumentException("Пароль неверный");
        }
        return user;
    }

    @Override
    public SaveUserDto aboutMe(){
        return conversionService.convert(repo.findByEmail(SecurityContextHolder.getContext().getAuthentication()
                .getName()).isPresent(), SaveUserDto.class);
    }

    private String createLink(String email, UUID uuid) {
        return "http://localhost:80/api/v1/users/verification?code="
                + uuid + "&email=" + email;
    }

    private static void sendGET(String to, String text) throws IOException {
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
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
