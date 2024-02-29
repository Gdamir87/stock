package ru.damir.stock.utils;

import lombok.*;
import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.entity.Product;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static ProductDto toDto(Product entity) {
        return ProductDto.builder()
                .currentId(entity.getId())
                .build();
    }
}
