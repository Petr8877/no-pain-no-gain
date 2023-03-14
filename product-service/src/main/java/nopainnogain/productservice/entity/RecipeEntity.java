package nopainnogain.productservice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "recipe")
public class RecipeEntity {

    @Id
    private UUID uuid;

    private LocalDateTime dtCreate;

    @Version
    private LocalDateTime dtUpdate;

    private String title;

    @ElementCollection
    @CollectionTable(schema = "app", name = "ingredient", joinColumns = @JoinColumn(name = "recipeId"))
    private List<IngredientEntity> ingredients;

    public RecipeEntity() {
    }

    public RecipeEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String title, List<IngredientEntity> ingredients) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.ingredients = ingredients;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }
}
