package ru.damir.stock.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Product {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Double price;

    /**
     * количество на складе
     */
    private Integer quantity;

}
