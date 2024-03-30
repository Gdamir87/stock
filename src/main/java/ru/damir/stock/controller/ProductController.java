package ru.damir.stock.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.damir.stock.dto.ProductDto;
import ru.damir.stock.dto.StatusResponse;
import ru.damir.stock.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Добавить новый товар
     * @param productDto Данные для добавления нового товара
     */
    @PostMapping
    public ProductDto create(@Valid @RequestBody ProductDto productDto) {
        log.info("[API] Request to create new product {}", productDto);
        return productService.create(productDto);
    }

    /**
     * Получить все товары
     * return Список всех категорий
     */
    @GetMapping
    public List<ProductDto> getAll() {
        log.info("[API] Request to get all products");
        return productService.getAllProducts();
    }

    /**
     * Получить товар по id
     * @param id Данные id для получения товара
     * return productDto
     */
    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        log.info("[API] Request to get product with id {}", id);
        return productService.getById(id);
    }

    /**
     * Обновить товар по id
     * @param id Данные id для изменения товара
     * return productDto
     */
    @PostMapping("/{id}")
    public ProductDto update(@PathVariable Long id, @RequestBody ProductDto productDto) {
        log.info("[API] Request to update product with id {}", id);
        return productService.update(id, productDto);
    }

    /**
     * Удалить товар по id
     * @param id Данные id для удаления товара
     * return Статус
     */
    @DeleteMapping("/{id}")
    public StatusResponse delete(@PathVariable Long id) {
        log.info("[API] Request to delete product with id {}", id);
        return productService.delete(id);
    }

    /**
     * Удалить все товары
     * return Статус
     */
    @DeleteMapping
    public StatusResponse deleteAll() {
        log.info("[API] Request to delete all products");
        return productService.deleteAll();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductDto>> getCustomers(@RequestParam(defaultValue = "0") int page,
                                                         @Min (value = 1, message = "min 1") @RequestParam(defaultValue = "10") int size) {

        Page<ProductDto> productsPage = productService.getPageOfProducts(page, size);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Page-Number", String.valueOf(productsPage.getNumber()));
        headers.add("X-Page-Size", String.valueOf(productsPage.getSize()));

        return ResponseEntity.ok()
                .headers(headers)
                .body(productsPage);
    }
}
