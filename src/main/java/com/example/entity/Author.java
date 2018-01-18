package com.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Author  {
    private int authorId;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String country;
    private Set<Book> books;

    @Id
    @GeneratedValue
    @Column(name = "Author_ID", nullable = false)
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "First_Name", nullable = true, length = 15)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "Second_Name", nullable = true, length = 25)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

    @Basic
    @Column(name = "Patronymic", nullable = true, length = 20)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "Country", nullable = true, length = 30)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (authorId != author.authorId) return false;
        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) return false;
        if (lastName != null ? !lastName.equals(author.lastName) : author.lastName != null) return false;
        if (patronymic != null ? !patronymic.equals(author.patronymic) : author.patronymic != null) return false;
        if (country != null ? !country.equals(author.country) : author.country != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorId;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @ManyToMany(mappedBy = "authors")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
