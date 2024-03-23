package ru.damir.stock.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.entity.Product;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {

    public static void fillProduct(Product product,ProductDto productDto) {
        if (StringUtils.isBlank(productDto.getArticle()))
            productDto.setArticle(product.getArticle());
        if (StringUtils.isBlank(productDto.getName()))
            productDto.setName(product.getName());
        if ((StringUtils.isBlank(productDto.getDescription())) && (!StringUtils.isBlank(product.getDescription())))
            productDto.setDescription(product.getDescription());
        if ((productDto.getPrice() == null) || (productDto.getPrice().signum() < 1))
            productDto.setPrice(product.getPrice());
        if ((productDto.getQuantity() == null) || (productDto.getQuantity() < 1))
            productDto.setQuantity(product.getQuantity());
        if (StringUtils.isBlank(productDto.getCategoryName()))
            productDto.setCategoryName(product.getCategory().getName());
        product.setArticle(productDto.getArticle());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
    }

}
