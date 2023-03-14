package nopainnogain.productservice.repository;

import nopainnogain.productservice.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ProductRepository extends CrudRepository<ProductEntity, UUID> {

    boolean existsByTitle(String title);

    @Query("select c from ProductEntity c")
    Page<ProductEntity> findAllPage(Pageable pageable);
}
