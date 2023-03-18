package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.ProductDto;
import nopainnogain.productservice.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class ProductDtoToEntity implements Converter<ProductDto, Product> {
    @Override
    public Product convert(ProductDto source) {
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        return new Product(UUID.randomUUID(),
                           time,
                           time,
                           source.getTitle(),
                           source.getWeight(),
                           source.getCalories(),
                           source.getProteins(),
                           source.getFats(),
                           source.getCarbohydrates());
    }
}
