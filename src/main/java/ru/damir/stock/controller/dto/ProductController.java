package ru.damir.stock.controller.dto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import ru.damir.stock.service.ProductService;

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
        return new StatusResponse(productService.saveProduct(request)
                .getStatus());
    }

    @GetMapping("/products")
    public StatusResponse showAllProducts () {
        return productService.showAllProducts();
    }

    @GetMapping("/products/{id}")
    public StatusResponse showProductById (@PathVariable Long id) {
        return productService.showProductById(id);
    }
}
