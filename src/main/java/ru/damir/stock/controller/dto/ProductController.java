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
    public ProductDto addProduct (@Valid @RequestBody ProductDto request) {
        return productService.addProduct(request);
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
    public ProductDto updateProductById(@PathVariable Long id, @RequestBody ProductDto request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/products/{id}")
    public StatusResponse deleteProduct (@PathVariable Long id) {
        productService.deleteProduct(id);
        return new StatusResponse("Товар успешно удален");
    }

}
