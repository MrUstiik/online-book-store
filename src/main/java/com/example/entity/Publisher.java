package com.example.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Publisher {
    private int publisherId;
    private String title;
    private String site;
    private Address address;
    private Set<Book> books;

    @Id
    @GeneratedValue
    @Column(name = "Publisher_ID", nullable = false)
    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Basic
    @Column(name = "Title", nullable = true, length = 30)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Basic
    @Column(name = "Site", nullable = true, length = 30)
    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publisher publisher = (Publisher) o;

        if (publisherId != publisher.publisherId) return false;
        if (title != null ? !title.equals(publisher.title) : publisher.title != null) return false;
        if (site != null ? !site.equals(publisher.site) : publisher.site != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = publisherId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "Address_ID", referencedColumnName = "Address_ID")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToMany(mappedBy = "publisher")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
