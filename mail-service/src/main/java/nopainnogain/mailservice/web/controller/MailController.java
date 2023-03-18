package nopainnogain.mailservice.web.controller;

import nopainnogain.mailservice.core.dto.MailDto;
import nopainnogain.mailservice.service.EmailServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final EmailServiceImpl service;

    public MailController(EmailServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public void send(@NotNull @RequestBody MailDto mailDto){
        service.sendSimpleMessage(mailDto.to(), mailDto.subject(), mailDto.text());
    }
}
