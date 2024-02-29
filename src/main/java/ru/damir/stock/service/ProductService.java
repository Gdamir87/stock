package ru.damir.stock.service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import ru.damir.stock.controller.dto.ProductManagementRequest;
import ru.damir.stock.controller.dto.ProductResponse;
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

        String checkingDataResponse = checkRequestData(request);

        if (!checkingDataResponse.equals("ok")) {
            return new StatusResponse(checkingDataResponse);
        }

        if (productRepository.findByName(request.getName()).isPresent()) {
            return new StatusResponse("Такой товар уже существует");
        }

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        productRepository.save(product);

        return new StatusResponse("Новый товар добавлен");

    }

    public String checkRequestData (ProductManagementRequest request) {

        if (request.getName().isBlank()) {
            return "Укажите имя";
        }
        if (request.getDescription().isBlank()) {
            return "Укажите описание";
        }
        if (request.getPrice().isNaN()) {
            return "Установите стоимость";
        }
        if (request.getQuantity() < 1) {
            return "Укажите количество";
        }

        return "ok";

    }

    @Transactional
    public ProductResponse showAllProducts () {
        Iterable<Product> allProducts = productRepository.findAll();
        return new ProductResponse(allProducts.toString());
    }

    @Transactional
    public StatusResponse showProductById (Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()) {
            return new StatusResponse("Товара с таким id не существует");
        }
        Product product1 = product.get();
        return new StatusResponse(product1.toString());
    }

    @Transactional
    public StatusResponse updateProduct (Long id, ProductManagementRequest request) {

        String checkingDataResponse = checkRequestData(request);

        if (!checkingDataResponse.equals("ok")) {
            return new StatusResponse(checkingDataResponse);
        }

        if (productRepository.findById(id).isEmpty()) {
            return new StatusResponse("Такого товара не существует");
        }

        Product product = productRepository.findById(id).get();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());

        productRepository.save(product);

        return new StatusResponse("Данные успешно обновлены");




    }





}