package com.research.orderservice.service;

import com.research.orderservice.dto.OrderRequestDTO;
import com.research.orderservice.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(OrderRequestDTO requestDTO);

    OrderResponseDTO getOrderById(Long orderId);

    List<OrderResponseDTO> getAllOrders();

    void updateOrderStatus(Long orderId, String status);
}