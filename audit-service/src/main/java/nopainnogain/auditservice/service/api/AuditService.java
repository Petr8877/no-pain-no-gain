package nopainnogain.auditservice.service.api;

import nopainnogain.auditservice.core.dto.AuditDto;
import nopainnogain.auditservice.core.dto.CreateEntryDto;
import nopainnogain.auditservice.core.dto.PageDto;
import org.springframework.data.domain.Pageable;

public interface AuditService {

    void createEntry(CreateEntryDto createEntryDto);

    PageDto<AuditDto> getAuditPage(Pageable pageable);

    AuditDto getById(String id);
}
