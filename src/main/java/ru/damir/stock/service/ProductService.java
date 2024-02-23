package ru.damir.stock.service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import ru.damir.stock.controller.dto.ProductManagementRequest;
import ru.damir.stock.controller.dto.StatusResponse;
import ru.damir.stock.entity.Product;
import ru.damir.stock.repository.ProductRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public StatusResponse saveProduct(ProductManagementRequest request) {

        String name = request.getName();
        if (name.isBlank()) {return new StatusResponse("Name is Null");}

        String description = request.getDescription();
        if (description.isBlank()) {return new StatusResponse("Description is Null");}

        Double price = request.getPrice();
        if (price.isNaN()) {return new StatusResponse("Price is Null");}

        int quantity = request.getQuantity();
        if (quantity < 1) {return new StatusResponse("Quantity is Null");}

        if (productRepository.findByName(name).isPresent()) {
            return new StatusResponse("Такой товар уже существует");
        }

        Product product = Product.builder()
                        .name(name)
                        .description(description)
                        .price(price)
                        .quantity(quantity)
                        .build();

        productRepository.save(product);

        return new StatusResponse("Новый товар добавлен");

    }

    @Transactional
    public StatusResponse showAllProducts () {
        Iterable<Product> allProducts = productRepository.findAll();
        return new StatusResponse(allProducts.toString());
    }

    @Transactional
    public StatusResponse showProductById (Long id) {
        Optional<Product> product = productRepository.findById(id);
        return new StatusResponse(product.toString());
    }

//    @Transactional
//    public StatusResponse updateProduct (ProductManagementRequest request) {
//        String name = request.getName();
//        if (name.isBlank()) {return new StatusResponse("Name is Null");}
//
//        String description = request.getDescription();
//        if (description.isBlank()) {return new StatusResponse("Description is Null");}
//
//        Double price = request.getPrice();
//        if (price.isNaN()) {return new StatusResponse("Price is Null");}
//
//        int quantity = request.getQuantity();
//        if (quantity < 1) {return new StatusResponse("Quantity is Null");}
//
//        if (productRepository.findByName(name).isEmpty()) {
//            return new StatusResponse("Такого товара не существует");
//        } else productRepository
//
//
//    }

}