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
     * артикул товара
     */
    private String article;

    /**
     * наименование товара
     */
    private String name;

    /**
     * описание товара
     */
    private String description;

    /**
     * стоимость
     */
    private BigDecimal price;

    /**
     * количество на складе
     */
    private Long quantity;

//    /**
//     * количество на складе
//     */
//    @ToString.Exclude
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "category_id")
//    private Category category;
}
