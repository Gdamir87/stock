package ru.damir.stock.controller.dto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import ru.damir.stock.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class ProductController {

    private final ProductService productService;

    /**
     * Добавить новый товар.<br>
     *
     * @param request Данные для добавления нового товаара
     *
     */
    @PostMapping("/products")
    public StatusResponse addProduct (@Valid @RequestBody ProductManagementRequest request) {
        productService.saveProduct(request);
        return new StatusResponse("Новый товар добавлен");
    }

    @GetMapping("/products")
    public List<ProductDto> getAllProducts () {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/products/{id}")
    public StatusResponse updateProductById(@PathVariable Long id, @RequestBody ProductManagementRequest request) {
        productService.updateProduct(id, request);
        return new StatusResponse("Данные успешно обновлены");
    }

    @DeleteMapping("/products/{id}")
    public StatusResponse deleteProduct (@PathVariable Long id) {
        productService.deleteProduct(id);
        return new StatusResponse("Товар успешно удален");
    }

}
