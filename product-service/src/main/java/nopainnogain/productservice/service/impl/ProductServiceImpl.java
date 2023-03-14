package nopainnogain.productservice.service.impl;

import nopainnogain.productservice.core.dto.ConvertPageDto;
import nopainnogain.productservice.core.dto.PageDto;
import nopainnogain.productservice.core.dto.nutrition.ProductDto;
import nopainnogain.productservice.core.dto.nutrition.SaveProductDto;
import nopainnogain.productservice.core.exception.SingleErrorResponse;
import nopainnogain.productservice.entity.ProductEntity;
import nopainnogain.productservice.repository.ProductRepository;
import nopainnogain.productservice.service.api.IProductService;
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
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repo;

    private final ConversionService conversionService;

    public ProductServiceImpl(ProductRepository repo, ConversionService conversionService) {
        this.repo = repo;
        this.conversionService = conversionService;
    }

    @Override
    public void addProduct(ProductDto productDTO) {
        if (!repo.existsByTitle(productDTO.getTitle())) {
            repo.save(Objects.requireNonNull(conversionService.convert(productDTO, ProductEntity.class)));
        } else {
            throw new SingleErrorResponse("Продукт с таким наименованием уже существует");
        }
    }

    @Override
    public void updateProduct(UUID uuid, LocalDateTime dtUpdate, ProductDto productDTO) {
        ProductEntity productById = repo.findById(uuid).orElseThrow(()
                -> new SingleErrorResponse("Продукта с таким ид не существует"));
        if (Objects.equals(dtUpdate, productById.getDtUpdate())) {
            productById.setTitle(productDTO.getTitle());
            productById.setWeight(productDTO.getWeight());
            productById.setCalories(productDTO.getCalories());
            productById.setProteins(productDTO.getProteins());
            productById.setFats(productDTO.getFats());
            productById.setCarbohydrates(productDTO.getCarbohydrates());
            repo.save(productById);
        } else {
            throw new SingleErrorResponse("Не верная версия");
        }
    }

    @Override
    public PageDto<SaveProductDto> getProductPage(Pageable pageable) {
        Page<ProductEntity> allPage = repo.findAllPage(pageable);
        List<SaveProductDto> content = new ArrayList<>();
        for (ProductEntity product : allPage) {
            content.add(conversionService.convert(product, SaveProductDto.class));
        }
        return conversionService.convert(new ConvertPageDto(allPage,content), PageDto.class);
    }
}
