package com.abc.cinema.cinema_website.service;

import com.abc.cinema.cinema_website.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    private com.abc.cinema.cinema_website.repo.ReservationRepository reservationRepository;

    private final Set<String> reservedSeats = new HashSet<>();

    public synchronized boolean reserveSeat(String seat, String customerName, String movieName, String email) {
        if (reservedSeats.contains(seat)) {
            return false; // Seat is already booked
        }
        reservedSeats.add(seat);

        // Save the reservation to the database
        Reservation reservation = new Reservation();
        reservation.setSeat(seat);
        reservation.setCustomerName(customerName);
        reservation.setMovieName(movieName);
        reservation.setEmail(email);

        // Save the reservation to the database
        reservationRepository.save(reservation);
        return true; // Successfully reserved
    }

    public Set<String> getReservedSeats() {
        return reservedSeats;
    }
}
