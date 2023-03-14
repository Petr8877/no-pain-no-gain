package nopainnogain.productservice.repository;

import nopainnogain.productservice.entity.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RecipeRepository extends CrudRepository<RecipeEntity, UUID> {

    boolean existsByTitle(String title);

    @Query("select c from RecipeEntity c")
    Page<RecipeEntity> findAllPage(Pageable pageable);
}
