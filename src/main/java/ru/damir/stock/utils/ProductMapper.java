package ru.damir.stock.utils;

import lombok.*;
import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.controller.dto.ProductManagementRequest;
import ru.damir.stock.entity.Product;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .currentId(entity.getId())
                .currentArticle(entity.getArticle())
                .currentName(entity.getName())
                .currentDescription(entity.getDescription())
                .currentPrice(entity.getPrice())
                .currentQuantity(entity.getQuantity())
                .build();
    }

    public static Product toProduct(ProductManagementRequest request) {
        return Product.builder()
                .article(request.getArticle())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();
    }
}
