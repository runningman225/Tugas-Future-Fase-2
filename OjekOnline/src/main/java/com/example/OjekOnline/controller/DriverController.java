package com.example.OjekOnline.controller;

import com.example.OjekOnline.model.Driver;
import com.example.OjekOnline.service.DriverService;
import com.example.OjekOnline.service.ServiceImpl.DriverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/drivers")
    public List<Driver> findAllDrivers(){
        return driverService.findAll();
    }

    @GetMapping("/drivers/{id}")
    public Driver findDriverById(@PathVariable("id") int id){
        return driverService.findDriverById(id);
    }

    @PostMapping("/drivers")
    public ResponseEntity<?> createNewDriver(@RequestBody  Driver driver){
        return driverService.createDriver(driver);
    }
    @DeleteMapping("/drivers/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
        return driverService.deleteDriver(id);
    }

    @PutMapping("/drivers/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id,@RequestBody Driver driver){
        return driverService.updateDriverProfile(id,driver);
    }
}
