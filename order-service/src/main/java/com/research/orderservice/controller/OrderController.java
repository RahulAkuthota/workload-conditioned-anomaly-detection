package com.research.orderservice.controller;

import com.research.orderservice.dto.OrderRequestDTO;
import com.research.orderservice.dto.OrderResponseDTO;
import com.research.orderservice.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Create Order
    @PostMapping("/create")
    public OrderResponseDTO createOrder(
            @RequestBody OrderRequestDTO requestDTO) {

        return orderService.createOrder(requestDTO);
    }

    // Get Order By ID
    @GetMapping("/{id}")
    public OrderResponseDTO getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);
    }

    // Get All Orders
    @GetMapping
    public List<OrderResponseDTO> getAllOrders() {

        return orderService.getAllOrders();
    }

    // Update Order Status
    @PutMapping("/updateStatus/{id}")
    public String updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        orderService.updateOrderStatus(id, status);

        return "Order status updated successfully";
    }
}