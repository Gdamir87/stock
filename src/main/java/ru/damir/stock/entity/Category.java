//package ru.damir.stock.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "categories")
//public class Category {
//
//    /**
//     * id категории
//     */
//    @Id
//    private Long id;
//
//    /**
//     * название категории
//     */
//    private String name;
//
//    /**
//     * список товаров
//     */
//    @Builder.Default
//    @ToString.Exclude
//    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
//    private List<Product> products = new ArrayList<>();
//}
