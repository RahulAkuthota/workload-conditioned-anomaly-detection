package com.research.orderservice.dao.impl;

import com.research.orderservice.dao.OrderDao;
import com.research.orderservice.entity.Order;
import com.research.orderservice.entity.OrderItem;
import com.research.orderservice.entity.OrderStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createOrder(Order order) {
        entityManager.persist(order);
    }

    @Override
    public void createOrderItems(List<OrderItem> orderItems) {

        for (OrderItem item : orderItems) {
            entityManager.persist(item);
        }
    }

    @Override
    public Order getOrderById(Long orderId) {

        return entityManager.find(Order.class, orderId);
    }

    @Override
    public List<OrderItem> getOrderItems(Long orderId) {

        String query = "FROM OrderItem WHERE orderId = :orderId";

        return entityManager
                .createQuery(query, OrderItem.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    @Override
    public List<Order> getAllOrders() {

        String query = "FROM Order";

        return entityManager
                .createQuery(query, Order.class)
                .getResultList();
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {

        Order order = entityManager.find(Order.class, orderId);

        if (order != null) {
            order.setOrderStatus(OrderStatus.valueOf(status));
            entityManager.merge(order);
        }
    }
}