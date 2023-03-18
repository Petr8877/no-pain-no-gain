package nopainnogain.productservice.service.api;

import nopainnogain.productservice.core.dto.PageDto;
import nopainnogain.productservice.core.dto.nutrition.ProductDto;
import nopainnogain.productservice.core.dto.nutrition.SaveProductDto;
import nopainnogain.productservice.entity.Product;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IProductService {

    Product addProduct(ProductDto productDTO);

    Product updateProduct(UUID uuid, LocalDateTime dtUpdate, ProductDto productDTO);

    PageDto<SaveProductDto> getProductPage(Pageable pageable);
}
