package com.research.productservice.dao;

import com.research.productservice.entity.Product;
import java.util.List;

public interface ProductDao {

    void createProduct(Product product);

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    void updateProduct(Product product);

    void updateStock(Long productId, Integer quantity);
}