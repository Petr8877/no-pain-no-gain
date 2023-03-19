package nopainnogain.auditservice.web.controller;

import nopainnogain.auditservice.core.dto.AuditDto;
import nopainnogain.auditservice.core.dto.CreateEntryDto;
import nopainnogain.auditservice.core.dto.PageDto;
import nopainnogain.auditservice.core.dto.ToReportDto;
import nopainnogain.auditservice.service.api.AuditService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private final AuditService service;

    public AuditController(AuditService service) {
        this.service = service;
    }

    @PostMapping("/secretlink")
    public void createEntry(@RequestBody @Validated CreateEntryDto createEntryDto) {
        service.createEntry(createEntryDto);
    }

    @GetMapping("/toreport/{id}/from/{from}/to/{to}")
    public List<ToReportDto> reportInfo(@PathVariable("id") UUID id,
                                        @PathVariable("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo,
                                        @PathVariable("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom){
        return service.getReportInfo(id, dateTo, dateFrom);
    }

    @GetMapping
    public PageDto<AuditDto> getPage(
            @PageableDefault(page = 0, size = 20)
            Pageable pageable) {
        return service.getAuditPage(pageable);
    }

    @GetMapping("/{id}")
    public AuditDto getById(@PathVariable("id") String id) {
        return service.getById(id);
    }
}
