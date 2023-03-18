package nopainnogain.productservice.service.api;

import nopainnogain.productservice.core.dto.PageDto;
import nopainnogain.productservice.core.dto.nutrition.RecipeDto;
import nopainnogain.productservice.core.dto.nutrition.SaveRecipeDto;
import nopainnogain.productservice.entity.Recipe;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface IRecipeService {

    Recipe createRecipe(RecipeDto recipeDTO);

    PageDto<SaveRecipeDto> getRecipePage(Pageable pageable);

    Recipe updateRecipe(UUID uuid, LocalDateTime dtUpdate, RecipeDto recipeDTO);
}
