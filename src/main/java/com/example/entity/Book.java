package com.example.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Book")
public class Book {
    private int bookId;
    private String title;
    private Integer yearOfPublication;
    private Integer price;
    private String description;
    private Set<Author> authors;
    private Publisher publisher;
    private Category       category;
    private Set<Genre>     genres;
    private Set<OrderBook> orders;
    private String         publisherTitle;
    private List<Integer>  genreIds;
    private List<Integer>  authorIds;

    @ManyToOne
    @JoinColumn(name = "Category_ID", referencedColumnName = "ID")
    public Category getCategory() {
        return category;
    }

    public Book setCategory(Category category) {
        this.category = category;
        return this;
    }

    @Id
    @GeneratedValue
    @Column(name = "Book_ID", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "Title", nullable = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "Year_of_publication", nullable = true)
    public Integer getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(Integer yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    @Basic
    @Column(name = "Price", nullable = true)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "Description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (yearOfPublication != null ? !yearOfPublication.equals(book.yearOfPublication) : book.yearOfPublication != null)
            return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (yearOfPublication != null ? yearOfPublication.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Author_Book", joinColumns = {@JoinColumn(name = "Book_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Author_ID")})
    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @ManyToOne
    @JoinColumn(name = "Publisher_ID", referencedColumnName = "Publisher_ID")
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "genre_book", joinColumns = {@JoinColumn(name = "Book_ID")},
            inverseJoinColumns = {@JoinColumn(name = "Genre_ID")})
    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<OrderBook> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderBook> orderBooks) {
        this.orders = orderBooks;
    }

    @Transient
    public String getPublisherTitle() {
        return publisherTitle;
    }

    public void setPublisherTitle(String publisherTitle) {
        this.publisherTitle = publisherTitle;
    }

    @Transient
    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    @Transient
    public List<Integer> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(List<Integer> authorIds) {
        this.authorIds = authorIds;
    }
}
