package ru.damir.stock.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.damir.stock.dto.CategoryDto;
import ru.damir.stock.dto.ProductDto;
import ru.damir.stock.entity.Category;
import ru.damir.stock.entity.Product;

import java.util.List;
import java.util.stream.StreamSupport;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapper {
    public static Category toEntity(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }

    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .build();
    }

    public static List<CategoryDto> toDtoList(Iterable<Category> categories) {
        return StreamSupport.stream(categories.spliterator(), false)
                .map(CategoryMapper::toDto)
                .toList();
    }
}
