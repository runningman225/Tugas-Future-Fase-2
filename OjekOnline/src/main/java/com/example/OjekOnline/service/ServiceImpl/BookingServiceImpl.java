package com.example.OjekOnline.service.ServiceImpl;

import com.example.OjekOnline.model.Booking;
import com.example.OjekOnline.model.Driver;
import com.example.OjekOnline.model.Member;
import com.example.OjekOnline.repository.BookingRepository;
import com.example.OjekOnline.service.BookingService;
import com.example.OjekOnline.service.DriverService;
import com.example.OjekOnline.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DriverService driverService;

    @Override
    public ResponseEntity<?> createBooking(Booking booking) {
        if(booking.getMember().getBalance()>booking.getPrice()) {
            Booking bookingExist = bookingRepository.findById(booking.getId());
            if (bookingExist == null) {
                booking.setBookedDate(new Date().getTime());
                bookingRepository.save(booking);
                return new ResponseEntity("Booking saved!", HttpStatus.OK);
            }
        }
        return new ResponseEntity("Error, failed to do Booking", HttpStatus.BAD_REQUEST);
    }



    @Override
    public Booking findBookingById(int id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public ResponseEntity<?> deleteBooking(int id) {
        Booking bookingExist = bookingRepository.findById(id);
        if(bookingExist==null) {
            return new ResponseEntity("Failed to delete Booking!", HttpStatus.BAD_REQUEST);
        }
        bookingRepository.delete(bookingExist);
        return new ResponseEntity("Successed to delete Booking", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateBooking(int id, Booking newBooking) {
        Booking booking = bookingRepository.findById(id);
        if(booking == null)return new ResponseEntity("Booking not found!", HttpStatus.BAD_REQUEST);
        Driver driver = booking.getDriver();
        Member member = booking.getMember();
        if(newBooking.getStatusCode()==0) {
            booking.setStatus("Booking Accepted/Waiting for pick up");
            booking.setBookedDate(new Date().getTime());
            driverService.updateDriverBalance(driver.getId(),booking.getPrice(),true);
            memberService.updateMemberBalance(member.getId(),booking.getPrice(),true);

        }
        else if(newBooking.getStatusCode()==1) {
            booking.setStatus("Picked Up");
            booking.setPickedUpDate(new Date().getTime());
        }
        else if(newBooking.getStatusCode()==2) {
            booking.setStatus("Dropped off");
            booking.setDroppedOffDate(new Date().getTime());
        }else if(newBooking.getStatusCode()==3) {
            booking.setStatus("Booking Canceled");
            booking.setCanceledDate(new Date().getTime());
            driverService.updateDriverBalance(driver.getId(),booking.getPrice(),false);
            memberService.updateMemberBalance(member.getId(),booking.getPrice(),false);
        }
        else if(newBooking.getStatusCode()==4) {
            booking.setStatus("Booking Rejected By Driver");
            booking.setRejectedDate(new Date().getTime());
            driverService.updateDriverBalance(driver.getId(),booking.getPrice(),false);
            memberService.updateMemberBalance(member.getId(),booking.getPrice(),false);
        }


        return new ResponseEntity("Successed to update Booking", HttpStatus.OK);    }
}
