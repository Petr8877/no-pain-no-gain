package nopainnogain.productservice.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

import java.util.UUID;

@Embeddable
public class IngredientEntity {

    private UUID product;

    @ManyToOne
    @Transient
    private ProductEntity productEntity;

    private int weight;

    @ManyToOne
    @Transient
    private RecipeEntity recipeEntity;

    public IngredientEntity() {
    }

    public IngredientEntity(UUID product, int weight) {
        this.product = product;
        this.weight = weight;
    }

    public UUID getProduct() {
        return product;
    }

    public void setProduct(UUID product) {
        this.product = product;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
