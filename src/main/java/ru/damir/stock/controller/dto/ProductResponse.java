package ru.damir.stock.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    /**
     * Статус
     */
    private String products;

    @Override
    public String toString() {
        return products;
    }
}
