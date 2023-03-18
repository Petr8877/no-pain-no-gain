package nopainnogain.auditservice.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import nopainnogain.auditservice.core.enums.Role;
import org.springframework.lang.NonNull;

import java.util.UUID;

public record UserDto(@NonNull UUID uuid,
                      @NotEmpty @Email String email,
                      @NotEmpty String fio,
                      @NonNull Role role) {
}
