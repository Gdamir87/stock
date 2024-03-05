package ru.damir.stock.controller.dto;

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
public class ProductManagementRequest {

    /**
     * наименовани товара
     */
    @NotBlank (message = "Артикул не может быть пустым")
    private String article = "Default article";

    /**
     * наименование товара
     */
    @NotBlank (message = "Наименование не может быть пустым")
    private String name = "Default name";

    /**
     * описание товара
     */
    @NotBlank (message = "Описание товара не может быть пустым")
    private String description = "Default description";

    /**
     * стоимость
     */
    @DecimalMin(value = "0.00001", message = "Стоимость должна быть больше нуля")
    private BigDecimal price = BigDecimal.valueOf(1.00);

    /**
     * количество на складе
     */
    @NotNull (message = "Количество не может быть пустым")
    @Min(value = 1, message = "Количество должно быть больше нуля")
    @Max(value = 1000, message = "Количество не должно быть более 999")
    private Long quantity = 1L;
}
