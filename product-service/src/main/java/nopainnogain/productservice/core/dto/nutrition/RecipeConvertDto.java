package nopainnogain.productservice.core.dto.nutrition;


import nopainnogain.productservice.entity.RecipeEntity;

import java.util.List;

public record RecipeConvertDto(List<SaveIngredientDto> ingredient,
                               RecipeEntity recipeEntity,
                               RecipeCPFCDto recipeCPFCDto) {
}
