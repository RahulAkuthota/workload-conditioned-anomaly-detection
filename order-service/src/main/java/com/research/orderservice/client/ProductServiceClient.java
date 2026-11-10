package com.research.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PRODUCT_SERVICE_URL =
            "http://localhost:8082/api/products/";

    public ProductResponse getProductById(Long productId) {

        String url = PRODUCT_SERVICE_URL + productId;

        return restTemplate.getForObject(url, ProductResponse.class);
    }

    // DTO representing the Product Service response
    public static class ProductResponse {

        private Long productId;
        private String productName;
        private String description;
        private java.math.BigDecimal price;
        private Integer stockQuantity;

        public ProductResponse() {
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public java.math.BigDecimal getPrice() {
            return price;
        }

        public void setPrice(java.math.BigDecimal price) {
            this.price = price;
        }

        public Integer getStockQuantity() {
            return stockQuantity;
        }

        public void setStockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
        }
    }
}