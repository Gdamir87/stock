package ru.damir.stock.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.damir.stock.entity.Category;
//import ru.damir.stock.entity.Category;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    /**
     * Артикул товара
     */
    @NotBlank(message = "Артикул не может быть пустым")
    private String article;

    /**
     * Наименование товара
     */
    @NotBlank(message = "Наименование не может быть пустым")
    private String name;

    /**
     * Описание товара
     */
    private String description;

    /**
     * Стоимость товара
     */
    @NotNull(message = "Укажите стоимость")
    @DecimalMin(value = "0.00001", message = "Стоимость должна быть больше нуля")
    private BigDecimal price;

    /**
     * Количество товара на складе
     */
    @NotNull(message = "Количество не может быть пустым")
    @Min(value = 1, message = "Количество должно быть больше нуля")
    @Max(value = 1000, message = "Количество не должно быть более 999")
    private Long quantity;

    @NotBlank(message = "Укажите название категории")
    private String categoryName;
}