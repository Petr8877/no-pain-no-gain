package nopainnogain.userservice.core.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import nopainnogain.userservice.entity.Role;
import nopainnogain.userservice.entity.Status;
import org.springframework.lang.NonNull;

public record UserDto(@NotBlank @Email String email,
                      @NotBlank String fio,
                      @NonNull Role role,
                      @NonNull Status status,
                      @NotBlank String password) {

}
