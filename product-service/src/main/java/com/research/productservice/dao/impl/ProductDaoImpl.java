package com.research.productservice.dao.impl;

import com.research.productservice.dao.ProductDao;
import com.research.productservice.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product getProductById(Long productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    public List<Product> getAllProducts() {

        String query = "FROM Product";

        return entityManager
                .createQuery(query, Product.class)
                .getResultList();
    }

    @Override
    public void updateProduct(Product product) {
        entityManager.merge(product);
    }

    @Override
    public void updateStock(Long productId, Integer quantity) {

        Product product = entityManager.find(Product.class, productId);

        if (product != null) {
            product.setStockQuantity(quantity);
            entityManager.merge(product);
        }
    }
}