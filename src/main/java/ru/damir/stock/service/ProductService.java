package ru.damir.stock.service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.entity.Category;
import ru.damir.stock.exception.MyException;
import ru.damir.stock.entity.Product;
import ru.damir.stock.repository.CategoryRepository;
import ru.damir.stock.repository.ProductRepository;
import ru.damir.stock.utils.ProductMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductDto createProduct(ProductDto request) {
        if (productRepository.findByArticle(request.getArticle()).isPresent()) {
            throw new MyException("Такой товар уже существует");
        }
        Category category = findOrCreateCategory(request.getCategoryName());
    //    categoryRepository.save(category); // сохраняет за счет Cascade
        Product product = ProductMapper.toProduct(request);
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = IterableUtils.toList(productRepository.findAll());
        if (products.isEmpty()) throw new MyException("Список товаров пуст");
        return ProductMapper.toDto(products);
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new MyException("Такого товара не существует"));
        return ProductMapper.toDto(product);
    }

    public ProductDto updateProduct(Long id, ProductDto request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new MyException("Такого товара не существует"));
        product.setArticle(request.getArticle());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return ProductMapper.toDto(product);
    }


    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new MyException("Товара с таким id не существует");
        }
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    private Category findOrCreateCategory(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseGet(() -> Category.builder()
                        .name(categoryName)
                        .build());
    }
}