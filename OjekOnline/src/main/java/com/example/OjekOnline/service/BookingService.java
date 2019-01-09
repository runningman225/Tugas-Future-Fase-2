package com.example.OjekOnline.service;

import com.example.OjekOnline.model.Booking;
import com.example.OjekOnline.model.Driver;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {

    public ResponseEntity<?> createBooking(Booking driver);

    public Booking findBookingById(int id);

    public List<Booking> findAll();

    public ResponseEntity<?> deleteBooking(int id);

    public ResponseEntity<?> updateBooking(int id, Booking booking);
}
