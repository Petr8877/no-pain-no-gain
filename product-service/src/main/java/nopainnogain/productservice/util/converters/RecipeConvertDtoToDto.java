package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.RecipeConvertDto;
import nopainnogain.productservice.core.dto.nutrition.SaveRecipeDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
                                 BigDecimal.valueOf(source.recipeCPFCDto().proteins()).setScale(2, RoundingMode.FLOOR),
                                 BigDecimal.valueOf(source.recipeCPFCDto().fats()).setScale(2, RoundingMode.FLOOR),
                                 BigDecimal.valueOf(source.recipeCPFCDto().carbohydrates()).setScale(2, RoundingMode.FLOOR));
    }
}
