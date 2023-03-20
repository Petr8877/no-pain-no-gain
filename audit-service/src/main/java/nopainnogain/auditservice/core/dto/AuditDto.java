package nopainnogain.auditservice.core.dto;

import nopainnogain.auditservice.core.enums.Role;
import nopainnogain.auditservice.core.enums.TypeOfEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record AuditDto(UUID uuid,
                       LocalDateTime dtCreate,
                       String text,
                       TypeOfEntity type,
                       int idType,
                       UUID client,
                       String clientEmail,
                       String clientFio,
                       Role clientRole) {
}
