package com.example.service.interfaces;


import com.example.entity.Order;
import com.example.entity.OrderBook;
import com.example.entity.User;

import java.util.List;
import java.util.Set;

public interface OrderServiceInterface {
    Order addOrder(Order order);
    Order editOrder(Order order);
    void deleteOrder(Order order);
    List<Order> getAll();
    Order getById(int id);
    List<Order> getByUser(User user);
    Set<OrderBook> getBooks(Order order);
}
