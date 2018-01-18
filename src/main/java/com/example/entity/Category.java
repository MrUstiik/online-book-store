package com.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Category")
public class Category {
    private int       id;
    private String    title;
    private Set<Book> books;

    @Id @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public Category setTitle(String title) {
        this.title = title;
        return this;
    }

    @OneToMany(mappedBy = "publisher")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
