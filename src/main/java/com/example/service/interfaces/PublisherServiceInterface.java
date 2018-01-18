package com.example.service.interfaces;



import com.example.entity.Publisher;

import java.util.List;

public interface PublisherServiceInterface {
    List<Publisher> getAll();
    Publisher addPublisher(Publisher publisher);
    Publisher editPublisher(Publisher publisher);
    void deletePublisher(Publisher publisher);
    Publisher getById(int id);
    Publisher getByTitle(String title);
    List<Publisher> getByCity(String city);
    List<Publisher> getMostPopular(int count);
}
