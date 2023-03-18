package nopainnogain.productservice.util.converters;

import nopainnogain.productservice.core.dto.nutrition.SaveProductDto;
import nopainnogain.productservice.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ProductEntityToDto implements Converter<Product, SaveProductDto> {
    @Override
    public SaveProductDto convert(Product source) {
        return new SaveProductDto(source.getTitle(),
                                  source.getWeight(),
                                  source.getCalories(),
                                  source.getProteins(),
                                  source.getFats(),
                                  source.getCarbohydrates(),
                                  source.getUuid(),
                                  source.getDtCreate(),
                                  ZonedDateTime.of(source.getDtUpdate(), ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
