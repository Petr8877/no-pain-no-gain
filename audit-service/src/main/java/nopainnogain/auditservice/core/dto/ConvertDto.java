package nopainnogain.auditservice.core.dto;

import nopainnogain.auditservice.entity.Audit;
import org.springframework.data.domain.Page;

import java.util.List;

public record ConvertDto(Page<Audit> allPage,
                         List<AuditDto> content) {
}
