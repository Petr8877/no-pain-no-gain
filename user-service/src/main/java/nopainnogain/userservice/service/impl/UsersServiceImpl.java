package nopainnogain.userservice.service.impl;


import nopainnogain.userservice.core.dto.PageDto;
import nopainnogain.userservice.core.dto.user.DetailsDto;
import nopainnogain.userservice.core.dto.user.SaveUserDto;
import nopainnogain.userservice.core.dto.user.UserDto;
import nopainnogain.userservice.core.exception.SingleErrorResponse;
import nopainnogain.userservice.entity.User;
import nopainnogain.userservice.repository.UserRepository;
import nopainnogain.userservice.service.api.IUsersService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UsersServiceImpl implements IUsersService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    private final ConversionService conversionService;

    public UsersServiceImpl(UserRepository userRepository, PasswordEncoder encoder, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.conversionService = conversionService;
    }

    @Override
    public User createUser(@Validated UserDto userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        userRepository.save(Objects.requireNonNull(user));
        toAudit("Создание новогопользователя uuid: " + user.getUuid());
        return user;
    }

    @Override
    public SaveUserDto getUser(UUID id) {
        SaveUserDto saveUserDto = userRepository.existsById(id) ? conversionService.convert(userRepository.findById(id).get(), SaveUserDto.class) : null;
        if (saveUserDto != null) {
            toAudit("Просмотр информации о пользователе email: " + saveUserDto.email());
            return saveUserDto;
        } else {
            throw new SingleErrorResponse("Нет пользавателя с таким id");
        }
    }

    @Override
    public User updateUser(UUID id, LocalDateTime dtUpdate, UserDto userDTO) {
        User entity = userRepository.findById(id).orElseThrow(() ->
                new SingleErrorResponse("Нет пользователя для обновления с таким id"));
        if (Objects.equals(entity.getDtUpdate(), dtUpdate)) {
            entity.setEmail(userDTO.email());
            entity.setFio(userDTO.fio());
            entity.setRole(userDTO.role());
            entity.setStatus(userDTO.status());
            entity.setPassword(encoder.encode(userDTO.password()));
            toAudit("Корректировка информации о пользователе uuid: " + entity.getUuid());
            return userRepository.save(entity);
        } else {
            throw new SingleErrorResponse("Введена не верная версия");
        }
    }

    @Override
    public PageDto<SaveUserDto> getUsersPage(Pageable pageable) {
        Page<User> allPage = userRepository.findAllPage(pageable);
        List<SaveUserDto> content = new ArrayList<>();
        for (User user : allPage) {
            content.add(conversionService.convert(user, SaveUserDto.class));
        }
        toAudit("Просмотр информации о свех пользователях");
        return new PageDto<>(allPage.getNumber(), allPage.getSize(), allPage.getTotalPages(),
                allPage.getTotalElements(), allPage.isFirst(), allPage.getNumberOfElements(),
                allPage.isLast(), content);
    }


    private void toAudit(String text) {
        DetailsDto principal = (DetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            SendToAuditService.sendAudit(principal.getUuid(), principal.getUsername(), principal.getFio(), principal.getRole(), text);
        } catch (IOException e) {
            throw new SingleErrorResponse("Запись в аудит не удалась");
        }
    }
}
