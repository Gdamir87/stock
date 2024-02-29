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
@Table (name = "product")
public class Product {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

//    @Override
//    public String toString() {
//        return "id= " + id + '\'' +
//                ", name='" + name + '\'' +
//                ", description='" + description + '\'' +
//                ", price=" + price +
//                ", quantity=" + quantity +
//                '\n' +
//                '}';
//    }

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
    private Double price;

    /**
     * количество на складе
     */
    @Column (name = "quantity")
    private int quantity;

}
