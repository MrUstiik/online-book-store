package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class OrderBookPK implements Serializable {
    private int orderId;
    private int bookId;

    @Column(name = "Order_ID", nullable = false)
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "Book_ID", nullable = false)
    @Id
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderBookPK that = (OrderBookPK) o;

        if (orderId != that.orderId) return false;
        if (bookId != that.bookId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + bookId;
        return result;
    }
}
