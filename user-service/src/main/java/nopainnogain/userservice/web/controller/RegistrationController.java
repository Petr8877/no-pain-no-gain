package nopainnogain.userservice.web.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import nopainnogain.userservice.core.dto.user.*;
import nopainnogain.userservice.service.impl.JwtService;
import nopainnogain.userservice.service.api.IRegistrationUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class RegistrationController {

    private final IRegistrationUserService service;
    private final JwtService jwtService;

    public RegistrationController(IRegistrationUserService service, JwtService jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/registration")
    public void registration(@RequestBody @Validated RegistrationDto userRegistrationDTO) {
        service.registrationUser(userRegistrationDTO);
    }

    @GetMapping(path = "/verification")
    public void verification(@RequestParam("code") @NotEmpty String code,
                             @RequestParam("email") @NotEmpty @Email String email) {
        service.verification(code, email);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginDto loginDTO) {
        DetailsDto login = service.login(loginDTO);
        Map<String, Object> claim = jwtService.getClaim(login);
        return ResponseEntity.ok(jwtService.generationToken(claim, login));
    }

    @GetMapping(path = "/me")
    public ResponseEntity<SaveUserDto> aboutMe() {
        return ResponseEntity.ok(service.aboutMe());
    }
}
