package com.example.repository;


import com.example.entity.Order;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


//@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);
}
