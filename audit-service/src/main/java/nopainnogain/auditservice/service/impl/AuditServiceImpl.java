package nopainnogain.auditservice.service.impl;

import nopainnogain.auditservice.core.dto.*;
import nopainnogain.auditservice.core.exception.SingleErrorResponse;
import nopainnogain.auditservice.entity.Audit;
import nopainnogain.auditservice.repository.AuditRepository;
import nopainnogain.auditservice.service.api.AuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class AuditServiceImpl implements AuditService {

    private final AuditRepository repository;
    private final ConversionService conversionService;

    public AuditServiceImpl(AuditRepository repository, ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    @Override
    public void createEntry(CreateEntryDto createEntryDto) {
        repository.save(Objects.requireNonNull(conversionService.convert(createEntryDto, Audit.class)));
    }

    @Override
    public PageDto<AuditDto> getAuditPage(Pageable pageable) {
        Page<Audit> allPage = repository.findAllPage(pageable);
        List<AuditDto> content = new ArrayList<>();
        for (Audit audit : allPage) {
            content.add(conversionService.convert(audit, AuditDto.class));
        }
        toAudit("Просмотр всех данных аудита");
        return conversionService.convert(new ConvertDto(allPage, content), PageDto.class);
    }

    @Override
    public AuditDto getById(String id) {
        Audit audit = repository.findById(UUID.fromString(id)).orElseThrow(()
                -> new SingleErrorResponse("Audit not found"));
        toAudit("Просмотр сведений аудита по id: " + id);
        return conversionService.convert(audit, AuditDto.class);
    }

    @Override
    public List<ToReportDto> getReportInfo(UUID id, LocalDate dateTo, LocalDate dateFrom) {
        LocalDateTime dateTime = dateFrom.atTime(00,00,00,000);
        LocalDateTime from = dateFrom.atStartOfDay();
        LocalDateTime to = dateTo.atStartOfDay();
        List<Audit> allAudit = repository.findAllUser(id, from, to);
        List<ToReportDto> reportDtos = new ArrayList<>();
        for (Audit audit : allAudit) {
            reportDtos.add(conversionService.convert(audit, ToReportDto.class));
        }
        return reportDtos;
    }

    private void toAudit(String text) {
        DetailsDto principal = (DetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        createEntry(new CreateEntryDto(principal.getUuid(), principal.getUsername(), principal.getFio(),
                principal.getRole(), text, 4));
    }
}
