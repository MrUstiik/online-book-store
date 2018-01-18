package com.example.service.interfaces;



import com.example.entity.OrderBook;

import java.util.List;

public interface OrderBookServiceInterface {
    List<OrderBook> getByOrderId(int id);
    List<OrderBook> getByBookId(int id);
    List<OrderBook> getAll();
    OrderBook getByOrderIdAndBookId(int orderId, int bookId);
}
