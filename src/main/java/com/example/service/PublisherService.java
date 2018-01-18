package com.example.service;

import com.example.entity.Publisher;
import com.example.repository.PublisherRepository;
import com.example.service.interfaces.PublisherServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService implements PublisherServiceInterface {
    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    public Publisher addPublisher(Publisher publisher) {
        return publisherRepository.saveAndFlush(publisher);
    }

    public Publisher editPublisher(Publisher publisher) {
        return publisherRepository.saveAndFlush(publisher);
    }

    public void deletePublisher(Publisher publisher) {
        publisherRepository.delete(publisher);
    }

    public Publisher getById(int id) {
        return publisherRepository.findOne(id);
    }

    public Publisher getByTitle(String title) {
        return publisherRepository.findByTitle(title);
    }

    public List<Publisher> getByCity(String city) {
        return publisherRepository.findByCity(city);
    }

    @Override
    public List<Publisher> getMostPopular(int count) {
        return publisherRepository.findAll().stream().sorted(Comparator.comparingInt(p -> p.getBooks().size())).limit(count).collect(Collectors.toList());

    }
}
