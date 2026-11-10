package com.research.productservice.controller;

import com.research.productservice.dto.ProductRequestDTO;
import com.research.productservice.dto.ProductResponseDTO;
import com.research.productservice.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create Product
    @PostMapping("/create")
    public ProductResponseDTO createProduct(
            @RequestBody ProductRequestDTO requestDTO) {

        return productService.createProduct(requestDTO);
    }

    // Get Product By ID
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id);
    }

    // Get All Products
    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {

        return productService.getAllProducts();
    }

    // Update Product
    @PutMapping("/update/{id}")
    public ProductResponseDTO updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO requestDTO) {

        return productService.updateProduct(id, requestDTO);
    }

    // Update Stock
    @PutMapping("/updateStock/{id}")
    public String updateStock(
            @PathVariable Long id,
            @RequestParam Integer quantity) {

        productService.updateStock(id, quantity);

        return "Product stock updated successfully";
    }
}