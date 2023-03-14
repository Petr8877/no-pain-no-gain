package nopainnogain.productservice.core.dto.nutrition;

import java.util.UUID;

public record IngredientCPFCDto(UUID product,
                                int weight,
                                int calories,
                                double proteins,
                                double fats,
                                double carbohydrates) {
}
