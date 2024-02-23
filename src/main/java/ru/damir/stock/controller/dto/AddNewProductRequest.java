package ru.damir.stock.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddNewProductRequest {
    /**
     * наименование товара
     */
    //@NotEmpty
    private String name;

    /**
     * описание товара
     */
    //@NotEmpty
    private String description;

    /**
     * стоимость
     */
    //@NotNull
    private Double price;

    /**
     * количество на складе
     */
    //@NotNull
    //@Min(1)
    //@Max(1000)
    private Integer quantity;
}
