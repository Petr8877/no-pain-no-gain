package nopainnogain.userservice.util.converter;

import nopainnogain.userservice.core.dto.user.SaveUserDto;
import nopainnogain.userservice.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class UserEntityToSaveUserDto implements Converter<User, SaveUserDto> {

    @Override
    public SaveUserDto convert(User source) {
        return new SaveUserDto(source.getUuid(),
                               source.getDtCreate(),
                               ZonedDateTime.of(source.getDtUpdate(), ZoneId.systemDefault()).toInstant().toEpochMilli(),
                               source.getEmail(),
                               source.getFio(),
                               source.getRole(),
                               source.getStatus());
    }

}
