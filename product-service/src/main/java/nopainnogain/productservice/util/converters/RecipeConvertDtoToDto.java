package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.RecipeConvertDto;
import nopainnogain.productservice.core.dto.nutrition.SaveRecipeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class RecipeConvertDtoToDto implements Converter<RecipeConvertDto, SaveRecipeDto> {
    @Override
    public SaveRecipeDto convert(RecipeConvertDto source) {
        return new SaveRecipeDto(source.recipeEntity().getUuid(),
                                 source.recipeEntity().getDtCreate(),
                                 ZonedDateTime.of(source.recipeEntity().getDtUpdate(), ZoneId.systemDefault()).toInstant().toEpochMilli(),
                                 source.recipeEntity().getTitle(),
                                 source.ingredient(),
                                 source.recipeCPFCDto().weight(),
                                 source.recipeCPFCDto().calories(),
                                 source.recipeCPFCDto().proteins(),
                                 source.recipeCPFCDto().fats(),
                                 source.recipeCPFCDto().carbohydrates());
    }
}
