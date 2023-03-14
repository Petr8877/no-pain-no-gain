package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.IngredientDto;
import nopainnogain.productservice.core.dto.nutrition.RecipeDto;
import nopainnogain.productservice.entity.IngredientEntity;
import nopainnogain.productservice.entity.RecipeEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RecipeDtoToEntity implements Converter<RecipeDto, RecipeEntity> {
    @Override
    public RecipeEntity convert(RecipeDto source) {
        LocalDateTime dtCreate = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        List<IngredientEntity> ingredientEntities = new ArrayList<>();
        for (IngredientDto ingredientDto : source.composition()) {
            ingredientEntities.add(new IngredientEntity(ingredientDto.product(), ingredientDto.weight()));
        }
        return new RecipeEntity(UUID.randomUUID(), dtCreate, dtCreate, source.title(), ingredientEntities);
    }
}
