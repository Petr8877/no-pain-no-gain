package nopainnogain.auditservice.web.controller;

import nopainnogain.auditservice.core.dto.AuditDto;
import nopainnogain.auditservice.core.dto.CreateEntryDto;
import nopainnogain.auditservice.core.dto.PageDto;
import nopainnogain.auditservice.service.api.AuditService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
