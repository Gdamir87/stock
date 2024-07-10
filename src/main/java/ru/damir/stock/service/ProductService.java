package ru.damir.stock.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import ru.damir.stock.dto.ProductDto;
import ru.damir.stock.dto.StatusResponse;
import ru.damir.stock.entity.Category;
import ru.damir.stock.entity.Product;
import ru.damir.stock.exception.MyException;
import ru.damir.stock.exception.ProductExistException;
import ru.damir.stock.repository.ProductRepository;
import ru.damir.stock.utils.ProductMapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final EntityManager entityManager;

    /**
     * Получить товар по id
     * @param productDto Данные id для создания товара
     */
    @Transactional
    public ProductDto create(ProductDto productDto) {
        if (productRepository.findByArticle(productDto.getArticle()).isPresent()) {
            log.error("Current product {} is exists", productDto);
            throw new ProductExistException("Такой товар уже существует");
        }
        Category category = categoryService.findCategory(productDto);
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
            throw new ProductExistException("Список товаров пуст");
        }
        return ProductMapper.toDto(products);
    }

    /**
     * Получить товар по id
     * @param id Данные id для получения товара
     */
    public ProductDto getById(Long id) {
        Optional <Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.error("Product with id {} doesn't exist", id);
            throw new ProductExistException("Товара с таким id не существует");
        }
        return ProductMapper.toDto(productOptional.get());
    }

    /**
     * Получить товар по id
     * @param id Данные id для получения товара<br>
     * @param productDto Данные для обновления
     */
    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        Category category = categoryService.findCategory(productDto);
        Optional <Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            log.error("Product with id {} doesn't exist", id);
            throw new ProductExistException("Товара с таким id не существует");
        }
        Product product = productOptional.get();
        ProductMapper.fillProduct(product, productDto);
        product.setCategory(category);
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    /**
     * Удалить товар по id
     * @param id Данные id для получения товара
     * @return Статус
     */
    @Transactional
    public StatusResponse delete(Long id) {
        if (!productRepository.existsById(id)) {
            log.error("Product with id {} doesn't exists", id);
            throw new ProductExistException("Товара с таким id не существует");
        }
        productRepository.deleteById(id);
        log.info("Product with id {} was deleted", id);
        return new StatusResponse("Товар успешно удален");
    }

    /**
     * Удалить все товары
     * @return Статус
     */
    public StatusResponse deleteAll() {
        productRepository.deleteAll();
        log.info("All products was deleted");
        return new StatusResponse("Товары успешно удалены");
    }

    /**
     * Получить список всех товаров постранично
     * @param page Номер стартовой страницы
     * @param size Количество элементов на вывод
     */
    public Page<ProductDto> getPageOfProducts(int page, int size) {
        if (size < 1)
            throw new MyException("Количество страниц должно быть больше 0");
        Pageable pageRequest = PageRequest.of(page, size);
        List<ProductDto> allCustomers = ProductMapper.toDto((List<Product>) productRepository.findAll());
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), allCustomers.size());
        List<ProductDto> pageContent = allCustomers.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, allCustomers.size());
    }

    public List<ProductDto> getAllProductByCategory(String name, String lastName, Long age) {



        Map<String, List<Product>> productsByCategory = IterableUtils.toList(productRepository.findAll()).stream()
                .collect(Collectors.groupingBy(product -> product.getCategory().getName()));

        // key = Аксессуары, value = List.of(Product(id=1, name=чехол))

        List<Product> accessories = productsByCategory.get("Аксессуары");
        List<Product> mobiles = productsByCategory.get("Смартфоны");
        List<Product> tvs = productsByCategory.get("Телевизоры");

        // Вывести на экран аксессуары
        System.out.println(accessories);
        // TV удалить все товары
        productRepository.deleteAll(tvs);
        // Мобильные устройства вернуть в ответе метода
        return ProductMapper.toDto(mobiles);
    }
}