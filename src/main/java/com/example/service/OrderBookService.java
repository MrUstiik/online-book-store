package com.example.service;

import com.example.entity.OrderBook;
import com.example.repository.OrderBookRepository;
import com.example.service.interfaces.OrderBookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderBookService implements OrderBookServiceInterface {
    @Autowired
    private OrderBookRepository repository;

    @Override
    public List<OrderBook> getByOrderId(int id) {
        return repository.findByOrderId(id);
    }

    @Override
    public List<OrderBook> getByBookId(int id) {
        return repository.findByBookId(id);
    }

    @Override
    public List<OrderBook> getAll() {
        return repository.findAll();
    }

    @Override
    public OrderBook getByOrderIdAndBookId(int orderId, int bookId) {
        return repository.findByOrderIdAndBookId(orderId, bookId);
    }
}
