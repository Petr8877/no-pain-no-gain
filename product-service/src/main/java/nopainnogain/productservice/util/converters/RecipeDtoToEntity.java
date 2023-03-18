package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.IngredientDto;
import nopainnogain.productservice.core.dto.nutrition.RecipeDto;
import nopainnogain.productservice.entity.Ingredient;
import nopainnogain.productservice.entity.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class RecipeDtoToEntity implements Converter<RecipeDto, Recipe> {
    @Override
    public Recipe convert(RecipeDto source) {
        LocalDateTime dtCreate = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        List<Ingredient> ingredientEntities = new ArrayList<>();
        for (IngredientDto ingredientDto : source.composition()) {
            ingredientEntities.add(new Ingredient(ingredientDto.product(), ingredientDto.weight()));
        }
        return new Recipe(UUID.randomUUID(),
                          dtCreate,
                          dtCreate,
                          source.title(),
                          ingredientEntities);
    }
}
