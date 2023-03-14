package nopainnogain.productservice.core.dto.nutrition;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SaveRecipeDto(@NonNull UUID uuid,
                            @NonNull @JsonProperty("dt_create") LocalDateTime dtCreate,
                            @NotEmpty @JsonProperty("dt_update") long dtUpdate,
                            @NotEmpty String title,
                            @NonNull List<SaveIngredientDto> composition,
                            @NotEmpty int weight,
                            @NotEmpty int calories,
                            @NotEmpty double proteins,
                            @NotEmpty double fats,
                            @NotEmpty double carbohydrates) {
}
