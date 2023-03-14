package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.IngredientDto;
import nopainnogain.productservice.entity.IngredientEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientDtoToEntity implements Converter<IngredientDto, IngredientEntity> {
    @Override
    public IngredientEntity convert(IngredientDto source) {
        return new IngredientEntity(source.product(), source.weight());
    }
}
