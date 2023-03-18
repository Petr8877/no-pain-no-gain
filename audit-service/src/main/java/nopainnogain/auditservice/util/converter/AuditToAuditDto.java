package nopainnogain.auditservice.util.converter;

import nopainnogain.auditservice.core.dto.AuditDto;
import nopainnogain.auditservice.core.dto.UserDto;
import nopainnogain.auditservice.entity.Audit;
import nopainnogain.auditservice.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditToAuditDto implements Converter<Audit, AuditDto> {

    @Override
    public AuditDto convert(Audit source) {
        User user = source.getUser().get(0);
        UserDto userDto = new UserDto(user.getUuid(), user.getEmail(), user.getFio(), user.getRole());
        return new AuditDto(source.getUuid(),
                            source.getDtCreate(),
                            userDto,
                            source.getTest(),
                            source.getType(),
                            source.getId());
    }
}
