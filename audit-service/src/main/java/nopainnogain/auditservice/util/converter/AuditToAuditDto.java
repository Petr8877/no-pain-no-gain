package nopainnogain.auditservice.util.converter;

import nopainnogain.auditservice.core.dto.AuditDto;
import nopainnogain.auditservice.entity.Audit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditToAuditDto implements Converter<Audit, AuditDto> {

    @Override
    public AuditDto convert(Audit source) {
        return new AuditDto(source.getUuid(),
                            source.getDtCreate(),
                            source.getText(),
                            source.getType(),
                            source.getIdType(),
                            source.getClient(),
                            source.getClientEmail(),
                            source.getClientFio(),
                            source.getClientRole());
    }
}
