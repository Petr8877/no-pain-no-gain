package nopainnogain.productservice.service.api;

import nopainnogain.productservice.core.dto.PageDto;
import nopainnogain.productservice.core.dto.nutrition.RecipeDto;
import nopainnogain.productservice.core.dto.nutrition.SaveRecipeDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IRecipeService {

    void createRecipe(RecipeDto recipeDTO);

    PageDto<SaveRecipeDto> getRecipePage(Pageable pageable);

    void updateRecipe(UUID uuid, LocalDateTime dtUpdate, RecipeDto recipeDTO);
}
