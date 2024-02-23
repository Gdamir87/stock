package ru.damir.stock.controller.dto;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.damir.stock.entity.Product;
import ru.damir.stock.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ProductController {

    @Autowired
    private ProductService productService;


    /**
     * Выыести весь список товаров.<br>
     *
     * @return список товаров
     */
    @GetMapping("/products")
    public List<Product> showAllProducts () {
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    /**
     * Отписаться от всех товаров.<br>
     * Если пользователя не было в БД - логируем
     *
     * @param id Данные для поиска по id
     * @return товар
     */
    @GetMapping("/products/{id}")
    public Product getProduct (@PathVariable int id) {
        Product product = productService.getProduct(id);
        return product;
    }

    /**
     * Отписаться от всех товаров.<br>
     * Если пользователя не было в БД - логируем
     *
     * @param request Данные для добавления нового товаара
     * @return статус
     */
    @PostMapping("/products")
    public Product addProduct (Product product) {
        productService.saveProduct(product);
        return product;
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct (@PathVariable int id) {
        productService.deleteProduct(id);
    }

}
