package nopainnogain.auditservice.service.api;

import nopainnogain.auditservice.core.dto.AuditDto;
import nopainnogain.auditservice.core.dto.CreateEntryDto;
import nopainnogain.auditservice.core.dto.PageDto;
import nopainnogain.auditservice.core.dto.ToReportDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AuditService {

    void createEntry(CreateEntryDto createEntryDto);

    PageDto<AuditDto> getAuditPage(Pageable pageable);

    AuditDto getById(String id);

    List<ToReportDto> getReportInfo(UUID id, LocalDate dateTo, LocalDate dateFrom);
}
