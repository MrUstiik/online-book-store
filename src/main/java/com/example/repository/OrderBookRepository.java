package com.example.repository;

import com.example.entity.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Integer> {
    List<OrderBook> findByBookId(int id);

    List<OrderBook>  findByOrderId(int id);

    OrderBook findByOrderIdAndBookId(int orderId, int bookId);
}
