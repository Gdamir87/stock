package ru.damir.stock.utils;

import lombok.*;
import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.entity.Product;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .id(entity.getId())
                .article(entity.getArticle())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
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
}
