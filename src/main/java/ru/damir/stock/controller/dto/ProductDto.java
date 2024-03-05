package ru.damir.stock.controller.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    /**
     * id
     */
    private Long currentId;

    /**
     * артикул товара
     */
    private String currentArticle;

    /**
     * наименование товара
     */
    private String currentName;

    /**
     * описание товара
     */
    private String currentDescription;

    /**
     * стоимость
     */
    private BigDecimal currentPrice;

    /**
     * количество на складе
     */
    private Long currentQuantity;

}
