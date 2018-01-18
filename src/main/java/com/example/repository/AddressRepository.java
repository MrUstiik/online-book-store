package com.example.repository;

import com.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("AddressRepository")
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByCountry(String country);

    List<Address> findByRegion(String region);

    List<Address> findByCity(String city);

    List<Address> findByCityAndStreet(String city, String street);

    Address findByCityAndStreetAndHouse(String city, String street, String house);
}
