package nopainnogain.productservice.core.dto.nutrition;


import nopainnogain.productservice.entity.Recipe;

import java.util.List;

public record RecipeConvertDto(List<SaveIngredientDto> ingredient,
                               Recipe recipeEntity,
                               RecipeCPFCDto recipeCPFCDto) {
}
