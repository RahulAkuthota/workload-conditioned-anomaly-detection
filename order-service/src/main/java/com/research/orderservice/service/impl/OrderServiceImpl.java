package com.research.orderservice.service.impl;

import com.research.orderservice.client.ProductServiceClient;
import com.research.orderservice.client.ProductServiceClient.ProductResponse;
import com.research.orderservice.client.UserServiceClient;

import com.research.orderservice.dao.OrderDao;

import com.research.orderservice.dto.*;

import com.research.orderservice.entity.Order;
import com.research.orderservice.entity.OrderItem;
import com.research.orderservice.entity.OrderStatus;

import com.research.orderservice.service.OrderService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {

        // 1. Validate request
        if (requestDTO.getUserId() == null) {
            throw new RuntimeException("User ID is required");
        }

        if (requestDTO.getItems() == null || requestDTO.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one product");
        }

        // 2. Verify user exists
        Object user = userServiceClient.getUserById(requestDTO.getUserId());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 3. Initialize order
        Order order = new Order();

        order.setUserId(requestDTO.getUserId());
        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);

        BigDecimal totalAmount = BigDecimal.ZERO;

        List<OrderItem> orderItems = new ArrayList<>();

        // 4. Validate products and calculate amount
        for (OrderItemRequestDTO itemDTO : requestDTO.getItems()) {

            if (itemDTO.getProductId() == null ||
                    itemDTO.getQuantity() == null ||
                    itemDTO.getQuantity() <= 0) {

                throw new RuntimeException("Invalid product ID or quantity");
            }

            ProductResponse product =
                    productServiceClient.getProductById(itemDTO.getProductId());

            if (product == null) {
                throw new RuntimeException(
                        "Product not found: " + itemDTO.getProductId());
            }

            if (product.getStockQuantity() < itemDTO.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + product.getProductName());
            }

            BigDecimal subtotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(itemDTO.getQuantity()));

            totalAmount = totalAmount.add(subtotal);

            OrderItem orderItem = new OrderItem();

            orderItem.setProductId(product.getProductId());
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setUnitPrice(product.getPrice());

            orderItems.add(orderItem);
        }

        // 5. Save order
        order.setTotalAmount(totalAmount);

        orderDao.createOrder(order);

        // 6. Set generated order ID in order items
        for (OrderItem item : orderItems) {
            item.setOrderId(order.getOrderId());
        }

        // 7. Save order items
        orderDao.createOrderItems(orderItems);

        // 8. Prepare response
        return convertToDTO(order, orderItems);
    }

    @Override
    public OrderResponseDTO getOrderById(Long orderId) {

        Order order = orderDao.getOrderById(orderId);

        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        List<OrderItem> items = orderDao.getOrderItems(orderId);

        return convertToDTO(order, items);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {

        List<Order> orders = orderDao.getAllOrders();

        List<OrderResponseDTO> responseList = new ArrayList<>();

        for (Order order : orders) {

            List<OrderItem> items =
                    orderDao.getOrderItems(order.getOrderId());

            responseList.add(convertToDTO(order, items));
        }

        return responseList;
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {

        orderDao.updateOrderStatus(orderId, status);
    }

    // Entity to DTO conversion
    private OrderResponseDTO convertToDTO(
            Order order, List<OrderItem> items) {

        OrderResponseDTO response = new OrderResponseDTO();

        response.setOrderId(order.getOrderId());
        response.setUserId(order.getUserId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());
        response.setOrderStatus(order.getOrderStatus());

        List<OrderItemResponseDTO> itemResponses = new ArrayList<>();

        for (OrderItem item : items) {

            OrderItemResponseDTO itemDTO = new OrderItemResponseDTO();

            itemDTO.setProductId(item.getProductId());
            itemDTO.setQuantity(item.getQuantity());
            itemDTO.setUnitPrice(item.getUnitPrice());

            BigDecimal subtotal = item.getUnitPrice()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));

            itemDTO.setSubtotal(subtotal);

            itemResponses.add(itemDTO);
        }

        response.setItems(itemResponses);

        return response;
    }
}