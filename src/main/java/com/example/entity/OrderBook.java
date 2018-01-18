package com.example.entity;

import javax.persistence.*;

@Entity
@IdClass(OrderBookPK.class)
public class OrderBook {
    private int orderId;
    private int bookId;
    private Integer quantityOfBooks;
    private Order order;
    private Book book;

    @Id
    @Column(name = "Order_ID", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "Book_ID", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "Quantity_of_books", nullable = true)
    public Integer getQuantityOfBooks() {
        return quantityOfBooks;
    }

    public void setQuantityOfBooks(Integer quantityOfBooks) {
        this.quantityOfBooks = quantityOfBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderBook orderBook = (OrderBook) o;

        if (orderId != orderBook.orderId) return false;
        if (bookId != orderBook.bookId) return false;
        if (quantityOfBooks != null ? !quantityOfBooks.equals(orderBook.quantityOfBooks) : orderBook.quantityOfBooks != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + bookId;
        result = 31 * result + (quantityOfBooks != null ? quantityOfBooks.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Order_ID", referencedColumnName = "Order_ID", nullable = false, insertable = false, updatable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "Book_ID", referencedColumnName = "Book_ID", nullable = false, insertable = false, updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
