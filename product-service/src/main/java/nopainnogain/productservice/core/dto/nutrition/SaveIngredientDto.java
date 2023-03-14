package nopainnogain.productservice.core.dto.nutrition;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.util.UUID;

public record SaveIngredientDto(@NonNull UUID product,
                                @NotEmpty int weight,
                                @NotEmpty int calories,
                                @NotEmpty double proteins,
                                @NotEmpty double fats,
                                @NotEmpty double carbohydrates) {

}
