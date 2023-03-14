package nopainnogain.userservice.util.converter;

import nopainnogain.userservice.core.dto.user.UserDto;
import nopainnogain.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class UserDtoToUserEntity implements Converter<UserDto, User> {

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User convert(UserDto source) {
        LocalDateTime timeNow = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        return new User(UUID.randomUUID(), source.email(), source.fio(), encoder.encode(source.password()), timeNow, timeNow,
                source.role(), source.status());
    }

}
