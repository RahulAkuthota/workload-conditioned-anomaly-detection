package com.research.orderservice.dao;

import com.research.orderservice.entity.Order;
import com.research.orderservice.entity.OrderItem;

import java.util.List;

public interface OrderDao {

    void createOrder(Order order);

    void createOrderItems(List<OrderItem> orderItems);

    Order getOrderById(Long orderId);

    List<OrderItem> getOrderItems(Long orderId);

    List<Order> getAllOrders();

    void updateOrderStatus(Long orderId, String status);
}