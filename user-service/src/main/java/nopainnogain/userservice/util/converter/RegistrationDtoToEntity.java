package nopainnogain.userservice.util.converter;

import nopainnogain.userservice.core.dto.user.UserRegistrationDto;
import nopainnogain.userservice.entity.User;
import nopainnogain.userservice.entity.Role;
import nopainnogain.userservice.entity.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class RegistrationDtoToEntity implements Converter<UserRegistrationDto, User> {

    private final PasswordEncoder encoder;

    public RegistrationDtoToEntity(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public User convert(UserRegistrationDto source) {
        LocalDateTime timeNow = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        return new User(UUID.randomUUID(), source.email(), source.fio(), encoder.encode(source.password()), timeNow, timeNow,
                Role.USER, Status.WAITING_ACTIVATION);
    }
}
