package ru.damir.stock.controller.dto;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    /**
     * Id категории
     */
    private Long id;

    /**
     * Название категории
     */
    private String name;
}

