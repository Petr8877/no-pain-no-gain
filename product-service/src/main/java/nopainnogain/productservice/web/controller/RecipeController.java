package nopainnogain.productservice.web.controller;

import nopainnogain.productservice.core.dto.PageDto;
import nopainnogain.productservice.core.dto.nutrition.RecipeDto;
import nopainnogain.productservice.core.dto.nutrition.SaveRecipeDto;
import nopainnogain.productservice.service.api.IRecipeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/recipe")
public class RecipeController {

    private final IRecipeService service;

    public RecipeController(IRecipeService service) {
        this.service = service;
    }

    @PostMapping
    public void addRecipe(@RequestBody @Validated RecipeDto recipeDTO) {
        service.createRecipe(recipeDTO);
    }

    @GetMapping
    public PageDto<SaveRecipeDto> getRecipePage(
            @PageableDefault(page = 0, size = 20)
            Pageable pageable) {
        return service.getRecipePage(pageable);
    }

    @PutMapping(path = "/{uuid}/dt_update/{dt_update}")
    public void updateRecipe(@PathVariable("uuid") UUID uuid, @PathVariable("dt_update") LocalDateTime dtUpdate,
                             @RequestBody @Validated RecipeDto recipeDTO) {
        service.updateRecipe(uuid, dtUpdate, recipeDTO);
    }
}
