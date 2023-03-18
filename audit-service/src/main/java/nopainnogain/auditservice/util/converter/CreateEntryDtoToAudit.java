package nopainnogain.auditservice.util.converter;

import nopainnogain.auditservice.core.dto.CreateEntryDto;
import nopainnogain.auditservice.core.enums.TypeOfEntity;
import nopainnogain.auditservice.core.exception.SingleErrorResponse;
import nopainnogain.auditservice.entity.Audit;
import nopainnogain.auditservice.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CreateEntryDtoToAudit implements Converter<CreateEntryDto, Audit> {
    @Override
    public Audit convert(CreateEntryDto source) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();
        User user = new User(source.uuid(), source.email(), source.fio(), source.role());
        TypeOfEntity typeOfEntity = getTypeOfEntityById(source.id());

        return new Audit(uuid,
                         time,
                         user,
                         source.text(),
                         typeOfEntity,
                         source.id());
    }

    private TypeOfEntity getTypeOfEntityById(int id) {
        switch (id) {
            case 1 -> {
                return TypeOfEntity.PRODUCT;
            }
            case 2 -> {
                return TypeOfEntity.RECIPE;
            }
            case 3 -> {
                return TypeOfEntity.USER;
            }
            case 4 -> {
                return TypeOfEntity.REPORT;
            }
        }
        throw new SingleErrorResponse("""
                Check id:
                1 - PRODUCT
                2 - RECIPE
                3 - USER
                4 - REPORT
                """);
    }
}
