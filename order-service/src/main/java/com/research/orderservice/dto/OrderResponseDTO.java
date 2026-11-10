package com.research.orderservice.dto;

import com.research.orderservice.entity.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderResponseDTO {

    private Long orderId;
    private Long userId;
    private LocalDate orderDate;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private List<OrderItemResponseDTO> items;

    public OrderResponseDTO() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponseDTO> items) {
        this.items = items;
    }
}