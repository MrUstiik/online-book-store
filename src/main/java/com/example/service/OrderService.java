package com.example.service;

import com.example.entity.Order;
import com.example.entity.OrderBook;
import com.example.entity.User;
import com.example.repository.OrderRepository;
import com.example.service.interfaces.OrderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrderService implements OrderServiceInterface {
    @Autowired
    private OrderRepository repository;

    public Order addOrder(Order order) {
        return repository.saveAndFlush(order);
    }

    public Order editOrder(Order order) {
        return repository.saveAndFlush(order);
    }

    public void deleteOrder(Order order) {
        repository.delete(order);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order getById(int id) {
        return repository.findOne(id);
    }

    public List<Order> getByUser(User user) {
        return repository.findByUser(user);
    }

    public Set<OrderBook> getBooks(Order order) {
        return repository.findOne(order.getOrderId()).getBooks();
    }
}
