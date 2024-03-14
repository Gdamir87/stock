package ru.damir.stock.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    /**
     * id продукта
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Артикул товара
     */
    private String article;

    /**
     * Наименование товара
     */
    private String name;

    /**
     * Описание товара
     */
    private String description;

    /**
     * Стоимость
     */
    private BigDecimal price;

    /**
     * Количество на складе
     */
    private Long quantity;

    /**
     * Количество на складе
     */
    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;
}
