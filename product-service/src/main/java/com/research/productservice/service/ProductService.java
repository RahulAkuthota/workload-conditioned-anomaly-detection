package com.research.productservice.service;

import com.research.productservice.dto.ProductRequestDTO;
import com.research.productservice.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO requestDTO);

    ProductResponseDTO getProductById(Long productId);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO requestDTO);

    void updateStock(Long productId, Integer quantity);
}