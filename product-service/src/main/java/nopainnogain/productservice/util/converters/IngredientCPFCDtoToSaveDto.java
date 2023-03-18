package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.IngredientCPFCDto;
import nopainnogain.productservice.core.dto.nutrition.SaveIngredientDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCPFCDtoToSaveDto implements Converter<IngredientCPFCDto, SaveIngredientDto> {
    @Override
    public SaveIngredientDto convert(IngredientCPFCDto source) {
        return new SaveIngredientDto(source.product(),
                                     source.weight(),
                                     source.calories(),
                                     source.proteins(),
                                     source.fats(),
                                     source.carbohydrates());
    }
}
