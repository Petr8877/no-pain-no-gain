package nopainnogain.productservice.core.dto.nutrition;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

public record SaveIngredientDto(@NonNull UUID product,
                                @NotEmpty int weight,
                                @NotEmpty int calories,
                                @NotEmpty BigDecimal proteins,
                                @NotEmpty BigDecimal fats,
                                @NotEmpty BigDecimal carbohydrates
) {

}
