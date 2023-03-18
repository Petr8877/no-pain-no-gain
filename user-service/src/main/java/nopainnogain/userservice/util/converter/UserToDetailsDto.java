package nopainnogain.userservice.util.converter;

import nopainnogain.userservice.core.dto.user.DetailsDto;
import nopainnogain.userservice.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDetailsDto implements Converter<User, DetailsDto> {
    @Override
    public DetailsDto convert(User source) {
        return new DetailsDto(source.getRole(),
                              source.getPassword(),
                              source.getEmail(),
                              source.getDtCreate(),
                              source.getDtUpdate(),
                              source.getFio(),
                              source.getUuid(),
                              source.getStatus());
    }
}
