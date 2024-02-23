package ru.damir.stock.service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import ru.damir.stock.controller.dto.ProductDAO;
import ru.damir.stock.entity.Product;


import java.util.List;

@Service
public class ProductService {

@Autowired
private ProductDAO productDAO;

    @Transactional
    public List<Product> getAllProducts () {
        return productDAO.getAllProducts();
    }

    @Transactional
    public void saveProduct (Product product) {
        productDAO.saveProduct(product);
    }

    @Transactional
    public Product getProduct (int id) {
        return  productDAO.getProduct(id);
    }

    @Transactional
    public void deleteProduct (int id) {
        productDAO.deleteProduct(id);
    }
}
