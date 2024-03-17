package ru.damir.stock.service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.entity.Category;
import ru.damir.stock.exception.MyException;
import ru.damir.stock.entity.Product;
import ru.damir.stock.repository.CategoryRepository;
import ru.damir.stock.repository.ProductRepository;
import ru.damir.stock.utils.Utils;
import ru.damir.stock.utils.ProductMapper;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductDto create(ProductDto productDto) {
        if (productRepository.findByArticle(productDto.getArticle()).isPresent()) {
            log.error("Current product {} is exists", productDto);
            throw new MyException("Такой товар уже существует");
        }
        String categoryName = productDto.getCategoryName();
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new MyException("Категории с таким названием не существует"));
        Product product = ProductMapper.toProduct(productDto);
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = IterableUtils.toList(productRepository.findAll());
        if (products.isEmpty()) throw new MyException("Список товаров пуст");
        return ProductMapper.toDto(products);
    }

    public ProductDto get(Long id) {
        Product product = productGetter(id);
        return ProductMapper.toDto(product);
    }

    private Product productGetter(Long id) {
        Optional <Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.error("Product with id {} doesn't exist", id);
            throw new MyException("Товара с таким id не существует");
        }
        return productOptional.get();
    }

    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productGetter(id);
        Category category = getCategory(productDto);
        Utils.updateHandler(product, productDto);
        Utils.fillProduct(product, productDto);
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    private Category getCategory(ProductDto productDto) {
        Optional <Category> categoryOptional = categoryRepository.findByName(productDto.getCategoryName());
        if (categoryOptional.isEmpty()) {
            log.error("Category {} doesn't exist", productDto.getCategoryName());
            throw new MyException("Категории с таким названием не существует");
        }
        return categoryOptional.get();
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            log.error("Product with id {} doesn't exists", id);
            throw new MyException("Товара с таким id не существует");
        }
        productRepository.deleteById(id);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

}