package nopainnogain.auditservice.core.dto;

import nopainnogain.auditservice.core.enums.TypeOfEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record AuditDto(UUID uuid,
                       LocalDateTime dtCreate,
                       UserDto user,
                       String text,
                       TypeOfEntity type,
                       int id) {
}
