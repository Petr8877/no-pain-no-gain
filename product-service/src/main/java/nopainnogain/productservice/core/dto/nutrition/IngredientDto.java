package nopainnogain.productservice.core.dto.nutrition;

import jakarta.validation.constraints.Positive;
import org.springframework.lang.NonNull;

import java.util.UUID;

public record IngredientDto(@NonNull UUID product, @Positive int weight) {

}
