package ru.damir.stock.controller.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {


    /**
     * id
     */
    private Long id;

    /**
     * Артикул товара
     */
    @NotBlank (message = "Артикул не может быть пустым")
    private String article;

    /**
     * Наименование товара
     */
    @NotBlank (message = "Наименование не может быть пустым")
    private String name;

    /**
     * Описание товара
     */
    @NotBlank (message = "Описание товара не может быть пустым")
    private String description;

    /**
     * Стоимость товара
     */
    @DecimalMin(value = "0.00001", message = "Стоимость должна быть больше нуля")
    private BigDecimal price;

    /**
     * Количество товара на складе
     */
    @NotNull (message = "Количество не может быть пустым")
    @Min(value = 1, message = "Количество должно быть больше нуля")
    @Max(value = 1000, message = "Количество не должно быть более 999")
    private Long quantity;
}