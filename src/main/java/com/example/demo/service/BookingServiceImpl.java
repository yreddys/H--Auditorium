package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepo;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    @Override
    public Booking createBooking(Booking booking) {
        // Save the booking using the repository
        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> findAllBookings() {
        return bookingRepo.findAll();
    }
}

