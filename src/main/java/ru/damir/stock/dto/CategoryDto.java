package ru.damir.stock.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    /**
     * Название категории
     */
    @NotBlank(message = "Наименование не может быть пустым")
    private String name;
}