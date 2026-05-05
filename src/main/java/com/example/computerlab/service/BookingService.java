package com.example.computerlab.service;

import com.example.computerlab.enums.BookingStatus;
import com.example.computerlab.enums.ComputerStatus;
import com.example.computerlab.model.Booking;
import com.example.computerlab.model.Computer;
import com.example.computerlab.model.User;
import com.example.computerlab.repository.BookingRepository;
import com.example.computerlab.repository.ComputerRepository;
import com.example.computerlab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ComputerRepository computerRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    public Booking createBooking(Booking booking) {
        Long userId = booking.getUser().getId();
        Long computerId = booking.getComputer().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Computer computer = computerRepository.findById(computerId)
                .orElseThrow(() -> new RuntimeException("Computer not found with id: " + computerId));

        if (computer.getStatus() == ComputerStatus.BROKEN ||
                computer.getStatus() == ComputerStatus.MAINTENANCE) {
            throw new RuntimeException("This computer cannot be booked because it is broken or under maintenance.");
        }

        booking.setUser(user);
        booking.setComputer(computer);

        if (booking.getStatus() == null) {
            booking.setStatus(BookingStatus.ACTIVE);
        }

        computer.setStatus(ComputerStatus.BOOKED);
        computerRepository.save(computer);

        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking updatedBooking) {
        Booking existingBooking = getBookingById(id);

        existingBooking.setStartTime(updatedBooking.getStartTime());
        existingBooking.setEndTime(updatedBooking.getEndTime());
        existingBooking.setPurpose(updatedBooking.getPurpose());
        existingBooking.setStatus(updatedBooking.getStatus());

        return bookingRepository.save(existingBooking);
    }

    public void deleteBooking(Long id) {
        Booking existingBooking = getBookingById(id);
        bookingRepository.delete(existingBooking);
    }
}