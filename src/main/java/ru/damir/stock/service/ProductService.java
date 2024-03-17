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
    public ProductDto createProduct(ProductDto productDto) {
        if (productRepository.findByArticle(productDto.getArticle()).isPresent()) {
            log.error("Current product {} is exists", productDto);
            throw new MyException("Такой товар уже существует");
        }
        String categoryName = productDto.getCategoryName();
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new MyException("Такой категории не существует"));
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

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MyException("Такого товара не существует"));
        return ProductMapper.toDto(product);
    }

    @Transactional
    public ProductDto productUpdate(Long id, ProductDto productDto) {
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new MyException("Такого товара не существует"));
        Optional <Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.error("Product with id {} doesn't exist", id);
            throw new MyException("Товара с таким id не существует");
        }
        Product product = productOptional.get();

        Optional <Category> categoryOptional = categoryRepository.findByName(productDto.getCategoryName());
        if (categoryOptional.isEmpty()) {
            log.error("Category {} doesn't exist", productDto.getCategoryName());
            throw new MyException("Категории с таким названием не существует");
        }
//        Category category = categoryRepository.findByName(productDto.getCategoryName())
//                .orElseThrow(() -> new MyException("Такой категории не существует"));
        Category category = categoryOptional.get();
        ProductDto updatedDto = updateHandler(product, productDto);
        Utils.fillProduct(product, updatedDto);
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    @Transactional
    public ProductDto updateHandler(Product product,ProductDto productDto) {
        if (StringUtils.isBlank(productDto.getArticle()))
            productDto.setArticle(product.getArticle());
        if (StringUtils.isBlank(productDto.getName()))
            productDto.setName(product.getName());
        if ((StringUtils.isBlank(productDto.getDescription())) && (!StringUtils.isBlank(product.getDescription())))
            productDto.setDescription(product.getDescription());
        if ((productDto.getPrice() == null) || (productDto.getPrice().signum() < 1))
            productDto.setPrice(product.getPrice());
        if ((productDto.getQuantity() == null) || (productDto.getQuantity() < 1))
            productDto.setQuantity(product.getQuantity());
        if (StringUtils.isBlank(productDto.getCategoryName()))
            productDto.setCategoryName(product.getCategory().getName());
        return productDto;
    }

    @Transactional
    public void deleteProduct(Long id) {
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