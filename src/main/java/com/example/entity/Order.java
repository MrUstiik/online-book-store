package com.example.entity;



import com.example.entity.enums.Status;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "`Order`")
public class Order {
    private int orderId;
    private Timestamp date;
    private double amount = 0;
    private Status status = Status.NEW;
    private User user;
    private Set<OrderBook> books;

    @Id
    @GeneratedValue
    @Column(name = "Order_ID", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "[Date]", nullable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "Amount", nullable = true)
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "Status", nullable = false)
    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (date != null ? !date.equals(order.date) : order.date != null) return false;
        if (Double.compare(amount, order.amount) != 0) return false;
        if (status != null ? !status.equals(order.status) : order.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = (int) (31 * result + amount);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "User_ID", referencedColumnName = "User_ID", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<OrderBook> getBooks() {
        return books;
    }

    public void setBooks(Set<OrderBook> orderBooks) {
        this.books = orderBooks;
    }
}
