package com.example.service;

import com.example.entity.Address;
import com.example.repository.AddressRepository;
import com.example.service.interfaces.AddressServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddressServiceInterface {

    @Autowired
    private AddressRepository addressRepository;


    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address addAddress(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    public Address editAddress(Address address) {
        return addressRepository.saveAndFlush(address);
    }

    public void deleteAddress(Address address) {
        addressRepository.delete(address);
    }

    public Address getById(int id) {
        return addressRepository.findOne(id);
    }

    public List<Address> getByCountry(String country) {
        return addressRepository.findByCountry(country);
    }

    public List<Address> getByRegion(String region) {
        return addressRepository.findByRegion(region);
    }

    public List<Address> getByCity(String city) {
        return addressRepository.findByCity(city);
    }

    public List<Address> getByCityAndStreet(String city, String street) {
        return addressRepository.findByCityAndStreet(city, street);
    }

    public Address getByCityAndStreetAndHouse(String city, String street, String house) {
        return addressRepository.findByCityAndStreetAndHouse(city, street, house);
    }
}
