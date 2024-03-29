package ru.damir.stock.service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import ru.damir.stock.dto.CategoryDto;
import ru.damir.stock.dto.ProductDto;
import ru.damir.stock.entity.Category;
import ru.damir.stock.exception.CategoryNotExistException;
import ru.damir.stock.entity.Product;
import ru.damir.stock.exception.ProductExistException;
import ru.damir.stock.exception.ProductNotExistException;
import ru.damir.stock.repository.CategoryRepository;
import ru.damir.stock.repository.ProductRepository;
import ru.damir.stock.utils.CategoryMapper;
import ru.damir.stock.utils.ProductMapper;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    /**
     * Получить товар по id<br>
     *
     * @param productDto Данные id для создания товара
     */
    @Transactional
    public ProductDto create(ProductDto productDto) {
        if (productRepository.findByArticle(productDto.getArticle()).isPresent()) {
            log.error("Current product {} is exists", productDto);
            throw new ProductExistException("Такой товар уже существует");
        }
        Category category = getCategory(productDto);
        Product product = ProductMapper.toProduct(productDto);
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    /**
     * Получить список всех товаров
     */
    public List<ProductDto> getAllProducts() {
        List<Product> products = IterableUtils.toList(productRepository.findAll());
        if (products.isEmpty()) {
            log.error("Products list is empty");
            throw new ProductNotExistException("Список товаров пуст");
        }
        return ProductMapper.toDto(products);
    }

    /**
     * Получить товар по id<br>
     *
     * @param id Данные id для получения товара
     */
    public ProductDto getById(Long id) {
        Product product = find(id);
        return ProductMapper.toDto(product);
    }

    private Product find(Long id) {
        Optional <Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.error("Product with id {} doesn't exist", id);
            throw new ProductNotExistException("Товара с таким id не существует");
        }
        return productOptional.get();
    }

    /**
     * Получить товар по id<br>
     *
     * @param id Данные id для получения товара<br>
     * @param productDto Данные для обновления
     */
    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        CategoryDto categoryDto = categoryService.find(productDto);

        Product product = find(id); // todo сделать через ProductService.get().
        //Product product = ProductMapper.toProduct(get(id)); // todo Конфликт бина, создается новый экземпляр

        ProductMapper.fillProduct(product, productDto);
        product.setCategory(CategoryMapper.toEntity(categoryDto));
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    /**
     * Удалить товар по id<br>
     *
     * @param id Данные id для получения товара
     */
    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            log.error("Product with id {} doesn't exists", id);
            throw new ProductNotExistException("Товара с таким id не существует");
        }
        productRepository.deleteById(id);
        log.info("Product with id {} was deleted", id);
    }

    /**
     * Удалить все товары
     */
    public void deleteAll() {
        productRepository.deleteAll();
        log.info("All products was deleted");
    }

}