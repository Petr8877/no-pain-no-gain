package nopainnogain.userservice.core.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import nopainnogain.userservice.entity.Role;
import nopainnogain.userservice.entity.Status;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record SaveUserDto(@NonNull UUID uuid,
                          @NonNull @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS") @JsonProperty("dt_create") LocalDateTime dtCreate,
                          @NotEmpty @JsonProperty("dt_update") long dtUpdate,
                          @NotEmpty @Email String email,
                          @NotEmpty String fio,
                          @NonNull Role role,
                          @NonNull Status status){

}
