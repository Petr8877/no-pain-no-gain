package nopainnogain.productservice.service.impl;

import nopainnogain.productservice.core.dto.DetailsDto;
import nopainnogain.productservice.core.dto.ConvertPageDto;
import nopainnogain.productservice.core.dto.PageDto;
import nopainnogain.productservice.core.dto.nutrition.ProductDto;
import nopainnogain.productservice.core.dto.nutrition.SaveProductDto;
import nopainnogain.productservice.core.exception.SingleErrorResponse;
import nopainnogain.productservice.entity.Product;
import nopainnogain.productservice.repository.ProductRepository;
import nopainnogain.productservice.service.api.IProductService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository repository;

    private final ConversionService conversionService;

    public ProductServiceImpl(ProductRepository repository, ConversionService conversionService) {
        this.repository = repository;
        this.conversionService = conversionService;
    }

    @Override
    public Product addProduct(ProductDto productDTO) {
        if (!repository.existsByTitle(productDTO.getTitle())) {
            Product product = repository.save(Objects.requireNonNull(conversionService.convert(productDTO, Product.class)));
            toAudit("Создание нового продукта uuid:" + product.getUuid());
            return product;
        } else {
            throw new SingleErrorResponse("Продукт с таким наименованием уже существует");
        }
    }

    @Override
    public Product updateProduct(UUID uuid, LocalDateTime dtUpdate, ProductDto productDTO) {
        Product productById = repository.findById(uuid).orElseThrow(()
                -> new SingleErrorResponse("Продукта с таким ид не существует"));
        if (Objects.equals(dtUpdate, productById.getDtUpdate())) {
            productById.setTitle(productDTO.getTitle());
            productById.setWeight(productDTO.getWeight());
            productById.setCalories(productDTO.getCalories());
            productById.setProteins(productDTO.getProteins());
            productById.setFats(productDTO.getFats());
            productById.setCarbohydrates(productDTO.getCarbohydrates());
            toAudit("Изменение продукта uuid:" + productById.getUuid());
            return repository.save(productById);
        } else {
            throw new SingleErrorResponse("Не верная версия");
        }
    }

    @Override
    public PageDto<SaveProductDto> getProductPage(Pageable pageable) {
        Page<Product> allPage = repository.findAllPage(pageable);
        List<SaveProductDto> content = new ArrayList<>();
        for (Product product : allPage) {
            content.add(conversionService.convert(product, SaveProductDto.class));
        }
        return conversionService.convert(new ConvertPageDto(allPage, content), PageDto.class);
    }

    private void toAudit(String text) {
        DetailsDto principal = (DetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            SendToAuditService.sendAudit(principal.getUuid(), principal.getUsername(), principal.getFio(),
                    principal.getRole(), text, 1);
        } catch (IOException e) {
            throw new SingleErrorResponse("Запись в аудит не удалась");
        }
    }
}
