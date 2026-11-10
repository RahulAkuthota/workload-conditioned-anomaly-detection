package com.research.productservice.service.impl;

import com.research.productservice.dao.ProductDao;
import com.research.productservice.dto.ProductRequestDTO;
import com.research.productservice.dto.ProductResponseDTO;
import com.research.productservice.entity.Product;
import com.research.productservice.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {

        Product product = new Product();

        product.setProductName(requestDTO.getProductName());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setStockQuantity(requestDTO.getStockQuantity());
        product.setCreatedAt(LocalDateTime.now());

        productDao.createProduct(product);

        return convertToDTO(product);
    }

    @Override
    public ProductResponseDTO getProductById(Long productId) {

        Product product = productDao.getProductById(productId);

        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }

        return convertToDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {

        List<Product> products = productDao.getAllProducts();

        List<ProductResponseDTO> responseList = new ArrayList<>();

        for (Product product : products) {
            responseList.add(convertToDTO(product));
        }

        return responseList;
    }

    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO requestDTO) {

        Product product = productDao.getProductById(productId);

        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }

        product.setProductName(requestDTO.getProductName());
        product.setPrice(requestDTO.getPrice());
        product.setStockQuantity(requestDTO.getStockQuantity());

        productDao.updateProduct(product);

        return convertToDTO(product);
    }

    @Override
    public void updateStock(Long productId, Integer quantity) {

        Product product = productDao.getProductById(productId);

        if (product == null) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }

        if (quantity < 0) {
            throw new RuntimeException("Stock quantity cannot be negative");
        }

        productDao.updateStock(productId, quantity);
    }

    // Entity to DTO conversion
    private ProductResponseDTO convertToDTO(Product product) {

        ProductResponseDTO responseDTO = new ProductResponseDTO();

        responseDTO.setProductId(product.getProductId());
        responseDTO.setProductName(product.getProductName());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setStockQuantity(product.getStockQuantity());
        responseDTO.setCreatedAt(product.getCreatedAt());

        return responseDTO;
    }
}