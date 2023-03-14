package nopainnogain.userservice.web.controller;

import nopainnogain.userservice.service.impl.JwtService;
import nopainnogain.userservice.core.dto.user.LoginDto;
import nopainnogain.userservice.core.dto.user.SaveUserDto;
import nopainnogain.userservice.core.dto.user.UserRegistrationDto;
import nopainnogain.userservice.service.api.IRegistrationUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
public class RegistrationController {

    private final IRegistrationUserService service;
    private final JwtService jwtService;

    public RegistrationController(IRegistrationUserService service, JwtService jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping(path = "/registration")
    public void registration(@RequestBody @Validated UserRegistrationDto userRegistrationDTO) {
        service.registrationUser(userRegistrationDTO);
    }

    @GetMapping(path = "/verification")
    public void verification(String code, String email) {
        service.verification(code, email);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginDto loginDTO) {
        var login = service.login(loginDTO);
        Map<String, Object> claim = jwtService.getClaim(login);
        return ResponseEntity.ok(jwtService.generationToken(claim, login));
    }

    @GetMapping(path = "/me")
    public ResponseEntity<SaveUserDto> aboutMe() {
        return ResponseEntity.ok(service.aboutMe());
    }
}
