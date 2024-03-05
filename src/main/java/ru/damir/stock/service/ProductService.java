package ru.damir.stock.service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import ru.damir.stock.controller.dto.ProductDto;
import ru.damir.stock.controller.dto.ProductManagementRequest;
import ru.damir.stock.controller.dto.StatusResponse;
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
    public void saveProduct(ProductManagementRequest request) {
        if (productRepository.findByArticle(request.getArticle()).isPresent()) {
            throw new RuntimeException("Такой товар уже существует");
        }
        Product product = ProductMapper.toProduct(request);
        productRepository.save(product);
    }

    @Transactional
    public List<ProductDto> getAllProducts() {
        List<Product> products = IterableUtils.toList(productRepository.findAll());
        List<ProductDto> productDtos = products.stream().map(ProductMapper::toDto).toList();
        return productDtos;
    }

    @Transactional
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого товара не существует"));
        ProductDto productDto = ProductMapper.toDto(product);
        return productDto;

    }

    @Transactional
    public void updateProduct(Long id, ProductManagementRequest request) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Такого товара не существует"));
        product.setArticle(request.getArticle());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Товара с таким id не существует");
        }
        productRepository.deleteById(id);
    }


}