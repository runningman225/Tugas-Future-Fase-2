package com.example.OjekOnline.service.ServiceImpl;

import com.example.OjekOnline.model.Driver;
import com.example.OjekOnline.model.Member;
import com.example.OjekOnline.repository.DriverRepository;
import com.example.OjekOnline.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;
//
//    public DriverServiceImpl(){
//    }

    @Override
    public ResponseEntity<?> createDriver(Driver driver) {
        Driver driverExist = driverRepository.findByStnk(driver.getStnk());
        if(driverExist==null) {
            driverRepository.save(driver);
            return new ResponseEntity("Driver saved!", HttpStatus.OK);
        }
        return new ResponseEntity("Error, failed to save driver", HttpStatus.BAD_REQUEST);
    }

    @Override
    public Driver findDriverById(int id) {
        return driverRepository.findById(id);
    }

    @Override
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public ResponseEntity<?>  deleteDriver(int id) {
        Driver driverExist = driverRepository.findById(id);
        if(driverExist==null) {
            return new ResponseEntity("Failed to delete Driver!", HttpStatus.BAD_REQUEST);
        }
        driverRepository.delete(driverExist);
        return new ResponseEntity("Successed to delete Driver", HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> updateDriverBalance(int id, double balance,boolean bool) {
        Driver driver = findDriverById(id);
        if(driver==null) return new ResponseEntity("driver not found!",HttpStatus.NOT_FOUND);
        if(bool){
            driver.setBalance(driver.getBalance()-balance);
        }else{
            driver.setBalance(driver.getBalance()+balance);
        }
        driverRepository.save(driver);
        return new ResponseEntity("Balance Updated",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?>  updateDriverProfile(int id, Driver newDriver) {
        Driver driver = driverRepository.findById(id);
        if(driver == null)return new ResponseEntity("Driver not found!", HttpStatus.BAD_REQUEST);
        driver.setBalance(newDriver.getBalance());
        driver.setName(newDriver.getName());
        driver.setStnk(newDriver.getStnk());
        driverRepository.save(driver);
        return new ResponseEntity("Successed to update Driver", HttpStatus.OK);
    }

}
