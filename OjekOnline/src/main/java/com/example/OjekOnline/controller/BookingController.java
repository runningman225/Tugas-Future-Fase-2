package com.example.OjekOnline.controller;

import com.example.OjekOnline.model.Booking;
import com.example.OjekOnline.model.Driver;
import com.example.OjekOnline.service.BookingService;
import com.example.OjekOnline.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public List<Booking> findALlBooking(){
        return bookingService.findAll();
    }

    @GetMapping("/booking/{id}")
    public Booking findBookingById(@PathVariable("id") int id){
        return bookingService.findBookingById(id);
    }

    @PostMapping("/booking")
    public ResponseEntity<?> createNewBooking(@RequestBody Booking booking){
        return bookingService.createBooking(booking);
    }
    @DeleteMapping("/booking/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable("id") int id){
        return deleteBooking(id);
    }
    @PutMapping("/drivers/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable("id") int id,@RequestBody Booking booking){
        return bookingService.updateBooking(id,booking);
    }
}
