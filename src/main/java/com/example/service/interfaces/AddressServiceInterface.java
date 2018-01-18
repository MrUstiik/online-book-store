package com.example.service.interfaces;



import com.example.entity.Address;

import java.util.List;

public interface AddressServiceInterface {
    List<Address> getAll();
    Address addAddress(Address address);
    Address editAddress(Address address);
    void deleteAddress(Address address);
    Address getById(int id);
    List<Address> getByCountry(String country);
    List<Address> getByRegion(String region);
    List<Address> getByCity(String city);
    List<Address> getByCityAndStreet(String city, String street);
    Address getByCityAndStreetAndHouse(String city, String street, String house);
}
