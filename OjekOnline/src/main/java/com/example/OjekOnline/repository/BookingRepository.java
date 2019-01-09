package com.example.OjekOnline.repository;

import com.example.OjekOnline.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    Booking findById(int id);
    Booking findByDriver(int id);
    Booking findByMember(int id);

}
