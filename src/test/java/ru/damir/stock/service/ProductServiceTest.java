package ru.damir.stock.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.entity.Product;
import ru.damir.stock.repository.ProductRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

//    @Test
//    void updateProduct_validData_successFilledAndSaved() {
//        ProductDto request = ProductDto.builder()
//                .article("123")
//                .build();
//        Long productId = 12L;
//        Mockito.when(productRepository.findById(productId))
//                .thenReturn(Optional.of(Product.builder().id(productId).build()));
//
//        Product result = productService.updateProduct(productId, request);
//
//        Assertions.assertEquals("123", result.getArticle());
//        Assertions.assertEquals(productId, result.getId());
//        Assertions.assertNull(result.getPrice());
//    }

    @Test
    void updateProduct_notFoundProduct_exception() {
        Mockito.when(productRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrowsExactly(RuntimeException.class,
                () -> productService.updateHandler(null, null));
    }

    @Test
    void saveProduct_ArticleExist_exception() {
        Mockito.when(productRepository.findByArticle(any()))
                .thenReturn(Optional.of(new Product()));

        RuntimeException exception = Assertions.assertThrowsExactly(RuntimeException.class,
                () -> productService.createProduct(new ProductDto()));
        Assertions.assertEquals("Такой товар уже существует", exception.getMessage());
    }
}