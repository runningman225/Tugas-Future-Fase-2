package com.example.OjekOnline.repository;

import com.example.OjekOnline.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Driver findById(int id);
    Driver findByStnk(String stnk);

}
