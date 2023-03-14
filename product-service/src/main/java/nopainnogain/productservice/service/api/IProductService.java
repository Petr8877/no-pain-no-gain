package nopainnogain.productservice.service.api;

import nopainnogain.productservice.core.dto.PageDto;
import nopainnogain.productservice.core.dto.nutrition.ProductDto;
import nopainnogain.productservice.core.dto.nutrition.SaveProductDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IProductService {

    void addProduct(ProductDto productDTO);

    void updateProduct(UUID uuid, LocalDateTime dtUpdate, ProductDto productDTO);

    PageDto<SaveProductDto> getProductPage(Pageable pageable);
}
