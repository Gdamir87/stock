package ru.damir.stock.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.damir.stock.controller.dto.CategoryDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapper {
    public static CategoryDto toDto (String categoryName) {
        return CategoryDto.builder()
                .name(categoryName)
                .build();
    }
}
