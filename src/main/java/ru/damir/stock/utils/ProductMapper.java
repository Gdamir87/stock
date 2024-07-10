package ru.damir.stock.utils;

import lombok.*;
import org.apache.commons.lang3.StringUtils;
import ru.damir.stock.dto.ProductDto;

import ru.damir.stock.entity.Product;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static ProductDto toDto(Product entity) {
        String categoryName = entity.getCategory().getName();
        return ProductDto.builder()
                .article(entity.getArticle())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .categoryName(categoryName)
                .build();
    }

    public static Product toProduct(ProductDto request) {
        return Product.builder()
                .article(request.getArticle())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }

    public static List<ProductDto> toDto(List<Product> products) {

        return products.stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    public static void fillProduct(Product product, ProductDto productDto) {
        if (StringUtils.isNotBlank(productDto.getArticle()))
            product.setArticle(productDto.getArticle());

        if (StringUtils.isNotBlank(productDto.getName()))
            product.setName(productDto.getName());

        if (productDto.getDescription() != null)
            product.setDescription(productDto.getDescription());

        if ((productDto.getPrice() != null) && (productDto.getPrice().signum() > 0))
            product.setPrice(productDto.getPrice());

        if ((productDto.getQuantity() != null) && (productDto.getQuantity() >= 1))
            product.setQuantity(productDto.getQuantity());

        if (StringUtils.isNotBlank(productDto.getCategoryName()))
            productDto.setCategoryName(product.getCategory().getName());
    }

}
