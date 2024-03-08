package ru.damir.stock.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "products")
public class Product {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    /**
     * артикул товара
     */
    @Column (name = "article")
    private String article;

    /**
     * наименование товара
     */
    @Column (name = "name")
    private String name;

    /**
     * описание товара
     */
    @Column (name = "description")
    private String description;

    /**
     * стоимость
     */
    @Column (name = "price")
    private BigDecimal price;

    /**
     * количество на складе
     */
    @Column (name = "quantity")
    private Long quantity;

}
