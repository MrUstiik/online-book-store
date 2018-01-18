package com.example.repository;

import com.example.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.entity.Address;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findByTitle(String title);

    @Query("select p from Publisher p WHERE :city = (select a.city from Address a WHERE a.addressId = p.address.addressId)")
    List<Publisher> findByCity(@Param("city") String city);
}
