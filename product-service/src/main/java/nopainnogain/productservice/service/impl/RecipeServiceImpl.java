package nopainnogain.productservice.service.impl;

import nopainnogain.productservice.core.dto.ConvertPageDto;
import nopainnogain.productservice.core.dto.PageDto;
import nopainnogain.productservice.core.dto.nutrition.*;
import nopainnogain.productservice.core.exception.SingleErrorResponse;
import nopainnogain.productservice.entity.IngredientEntity;
import nopainnogain.productservice.entity.ProductEntity;
import nopainnogain.productservice.entity.RecipeEntity;
import nopainnogain.productservice.repository.ProductRepository;
import nopainnogain.productservice.repository.RecipeRepository;
import nopainnogain.productservice.service.api.IRecipeService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class RecipeServiceImpl implements IRecipeService {

    private final RecipeRepository recipeRepo;
    private final ProductRepository productRepo;

    private final ConversionService conversionService;

    public RecipeServiceImpl(RecipeRepository repo, ProductRepository productRepo, ConversionService conversionService) {
        this.recipeRepo = repo;
        this.productRepo = productRepo;
        this.conversionService = conversionService;
    }

    @Override
    public void createRecipe(RecipeDto recipeDTO) {
        if (!recipeRepo.existsByTitle(recipeDTO.title())) {
            recipeRepo.save(Objects.requireNonNull(conversionService.convert(recipeDTO, RecipeEntity.class)));
        } else {
            throw new SingleErrorResponse("Рецепт с таким названием уже существует");
        }
    }


    @Override
    public PageDto<SaveRecipeDto> getRecipePage(Pageable pageable) {
        Page<RecipeEntity> allPage = recipeRepo.findAllPage(pageable);
        List<SaveRecipeDto> content = new ArrayList<>();
        for (RecipeEntity recipeEntity : allPage) {
            List<SaveIngredientDto> ingredientDtos = new ArrayList<>();
            List<IngredientCPFCDto> ingredientCPFCDtos = countIngredientCPFC(recipeEntity.getIngredients());
            for (IngredientCPFCDto ingredientCPFCDto : ingredientCPFCDtos) {
                ingredientDtos.add(conversionService.convert(ingredientCPFCDto, SaveIngredientDto.class));
            }
            content.add(conversionService.convert(new RecipeConvertDto(ingredientDtos, recipeEntity,
                    countRecipeCPFC(ingredientCPFCDtos)), SaveRecipeDto.class));
        }
        return conversionService.convert(new ConvertPageDto(allPage, content), PageDto.class);
    }

    @Override
    public void updateRecipe(UUID uuid, LocalDateTime dtUpdate, RecipeDto recipeDTO) {
        RecipeEntity recipeById = recipeRepo.findById(uuid).orElseThrow(()
                -> new SingleErrorResponse("Продукта с таким ид не существует"));
        if (Objects.equals(dtUpdate, recipeById.getDtUpdate())) {
            List<IngredientEntity> ingredients = new ArrayList<>();
            for (IngredientDto ingredientDto : recipeDTO.composition()) {
                ingredients.add(conversionService.convert(ingredientDto, IngredientEntity.class));
            }
            recipeById.setTitle(recipeDTO.title());
            recipeById.setIngredients(ingredients);
            recipeRepo.save(recipeById);
        } else {
            throw new SingleErrorResponse("Не верная версия");
        }
    }

    private List<IngredientCPFCDto> countIngredientCPFC(List<IngredientEntity> ingredientList) {
        List<IngredientCPFCDto> result = new ArrayList<>();
        for (IngredientEntity ingredient : ingredientList) {
            ProductEntity product = productRepo.findById(ingredient.getProduct()).orElseThrow(()
                    -> new SingleErrorResponse(("Продукта с данным ид нет")));
            int weight = ingredient.getWeight();
            double coif = (double) weight / product.getWeight();
            int calories = (int) (product.getCalories() * coif);
            double proteins = product.getProteins() * coif;
            double fats = product.getFats() * coif;
            double carbohydrates = product.getCarbohydrates() * coif;
            result.add(new IngredientCPFCDto(product.getUuid(), weight, calories, proteins, fats, carbohydrates));
        }
        return result;
    }

    private RecipeCPFCDto countRecipeCPFC(List<IngredientCPFCDto> ingredientCPFC) {
        int weight = 0;
        int calories = 0;
        double proteins = 0;
        double fats = 0;
        double carbohydrates = 0;
        for (IngredientCPFCDto dto : ingredientCPFC) {
            weight += dto.weight();
            calories += dto.calories();
            proteins += dto.proteins();
            fats += dto.fats();
            carbohydrates += dto.carbohydrates();
        }
        return new RecipeCPFCDto(weight, calories, proteins, fats, carbohydrates);
    }
}