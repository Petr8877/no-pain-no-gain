package nopainnogain.userservice.core.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record UserRegistrationDto(@NotEmpty @Email String email,
                                  @NotEmpty String fio,
                                  @NotEmpty String password) {

}
