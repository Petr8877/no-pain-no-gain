package nopainnogain.userservice.web.controller;

import nopainnogain.userservice.core.dto.PageDto;
import nopainnogain.userservice.core.dto.user.SaveUserDto;
import nopainnogain.userservice.core.dto.user.UserDto;
import nopainnogain.userservice.service.api.IUsersService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    private final IUsersService service;

    public UsersController(IUsersService service) {
        this.service = service;
    }

    @PostMapping
    public void addNewUser(@RequestBody @Validated UserDto userDTO) {
        service.createUser(userDTO);
    }

    @GetMapping
    public PageDto<SaveUserDto> getUsersPage(
            @PageableDefault(page = 0, size = 20)
            Pageable pageable){
        return service.getUsersPage(pageable);
    }

    @GetMapping(path = "/{id}")
    public SaveUserDto getUserById(@PathVariable("id") UUID id) {
        return service.getUser(id);
    }

    @PutMapping(path = "/{id}/dt_update/{dt_update}")
    public void updateUser(@PathVariable("id") UUID id,@PathVariable("dt_update") LocalDateTime dtUpdate,
                           @RequestBody @Validated UserDto userDTO) {
        service.updateUser(id, dtUpdate, userDTO);
    }
}
