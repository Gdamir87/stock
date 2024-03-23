package ru.damir.stock.utils;

import lombok.*;
import ru.damir.stock.controller.dto.ProductDto;

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

}
