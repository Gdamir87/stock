package ru.damir.stock.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.entity.Product;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {
    public static Product fillProduct(Product product, ProductDto productDto) {
        product.setArticle(productDto.getArticle());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        return product;
    }
}
