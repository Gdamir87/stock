package ru.damir.stock.controller.dto;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.damir.stock.entity.Product;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Product> getAllProducts() {
        Session session = entityManager.unwrap(Session.class);

        Query<Product> query = session.createQuery("from Product", Product.class);
        List<Product> allProducts = query.getResultList();
        return allProducts;

    }

    public void saveProduct(Product product) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(product);
    }

    public Product getProduct(int id) {
        Session session = entityManager.unwrap(Session.class);
        Product product = session.get(Product.class, id);
        return product;
    }

    public void deleteProduct(int id) {
        Session session = entityManager.unwrap(Session.class);
        Query<Product> query = session.createQuery("delete from Product " +
                "where id =: productId");
        query.setParameter("productId", id);
        query.executeUpdate();
    }
}
