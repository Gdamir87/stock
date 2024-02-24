package ru.damir.stock.controller.dto;

//import org.hibernate.Session;
//import org.hibernate.query.Query;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.damir.stock.entity.Product;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    private EntityManager entityManager;

    public List<Product> getAllProducts() {
//        Session session = entityManager.unwrap(Session.class);
//
//        Query<Product> query = session.createQuery("from Product", Product.class);
//        List<Product> allProducts = query.getResultList();

        Query query = entityManager.createQuery("from Product");
        List<Product> allProducts = query.getResultList();
        return allProducts;
    }

    public void saveProduct(Product product) {
//        Session session = entityManager.unwrap(Session.class);
//        session.persist(product);
        Product newProduct = entityManager.merge(product);
        product.setId(newProduct.getId());
    }

    public Product getProduct(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Product product = session.get(Product.class, id);

        Product product = entityManager.find(Product.class, id);
        return product;
    }

    public void deleteProduct(int id) {
//        Session session = entityManager.unwrap(Session.class);
//        Query<Product> query = session.createQuery("delete from Product " +
//                "where id =: productId");
//        query.setParameter("productId", id);
//        query.executeUpdate();
        Query query = entityManager.createQuery("delete from Product " +
                "where id =: productId");
        query.setParameter("productId", id);
        query.executeUpdate();
    }
}
