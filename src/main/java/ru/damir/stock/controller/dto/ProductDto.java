package ru.damir.stock.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Double currentPrice;

    /**
     * количество на складе
     */
    private int currentQuantity;

}
