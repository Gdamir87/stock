package ru.damir.stock.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.controller.dto.StatusResponse;
import ru.damir.stock.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Добавить новый товар<br>
     *
     * @param request Данные для добавления нового товара
     */
    @PostMapping
    public ProductDto createProduct(@Valid @RequestBody ProductDto request) {
        return productService.create(request);
    }

    /**
     * Получить все товары
     */
    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Получить товар по id<br>
     *
     * @param id Данные id для получения товара
     */
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.get(id);
    }

    /**
     * Обновить товар по id<br>
     *
     * @param id Данные id для изменения товара
     */
    @PostMapping("/{id}")
    public ProductDto updateProductById(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);
    }

    /**
     * Удалить товар по id<br>
     *
     * @param id Данные id для удаления товара
     */
    @DeleteMapping("/{id}")
    public StatusResponse deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new StatusResponse("Товар успешно удален");
    }

    /**
     * Удалить все товары
     */
    @DeleteMapping
    public StatusResponse deleteAll() {
        productService.deleteAll();
        return new StatusResponse("Товары успешно удалены");
    }
}
