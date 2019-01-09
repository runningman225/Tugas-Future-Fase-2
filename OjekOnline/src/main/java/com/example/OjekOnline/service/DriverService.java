package com.example.OjekOnline.service;

import com.example.OjekOnline.model.Driver;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    public ResponseEntity<?> createDriver(Driver driver);

    public Driver findDriverById(int id);

    public List<Driver> findAll();

    public ResponseEntity<?> deleteDriver(int id);

    public ResponseEntity<?> updateDriverBalance(int id, double balance, boolean bool);

    public ResponseEntity<?> updateDriverProfile(int id, Driver driver);


}
