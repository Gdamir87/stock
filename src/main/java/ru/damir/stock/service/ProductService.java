package ru.damir.stock.service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.controller.exception.MyException;
import ru.damir.stock.entity.Product;
import ru.damir.stock.repository.ProductRepository;
import ru.damir.stock.utils.ProductMapper;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductDto createProduct(ProductDto request) {
        if (productRepository.findByArticle(request.getArticle()).isPresent()) {
            throw new MyException("Такой товар уже существует");
        }
        Product product = ProductMapper.toProduct(request);
        productRepository.save(product);
        return ProductMapper.toDto(product);
    }

    @Transactional
    public List<ProductDto> getAllProducts() {

        dataExistChecking();
        List<Product> products = IterableUtils.toList(productRepository.findAll());
        List<ProductDto> productDtos = products.stream().map(ProductMapper::toDto).toList();
        return productDtos;
    }

    @Transactional
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new MyException("Такого товара не существует"));
        ProductDto productDto = ProductMapper.toDto(product);
        return productDto;
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new MyException("Такого товара не существует"));
        product.setArticle(request.getArticle());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        return ProductMapper.toDto(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new MyException("Товара с таким id не существует");
        }
        productRepository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {

        dataExistChecking();
        productRepository.deleteAll();
    }

    @Transactional
    public void dataExistChecking() {
        Iterable<Product> all = productRepository.findAll();
        if (IterableUtils.toList(all).isEmpty()) {
            throw new MyException("Список товаров пуст");
        }
    }


}