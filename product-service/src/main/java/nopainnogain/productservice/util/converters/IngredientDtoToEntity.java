package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.IngredientDto;
import nopainnogain.productservice.entity.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientDtoToEntity implements Converter<IngredientDto, Ingredient> {
    @Override
    public Ingredient convert(IngredientDto source) {
        return new Ingredient(source.product(),
                              source.weight());
    }
}
