package com.abc.cinema.cinema_website.service;

import com.abc.cinema.cinema_website.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    private com.abc.cinema.cinema_website.repo.ReservationRepository reservationRepository;

    private final Set<String> reservedSeats = new HashSet<>();

    // Updated to handle multiple seat reservations
    public synchronized boolean reserveSeats(List<String> seats, String customerName, String movieName, String email) {
        // Check if any of the selected seats are already reserved
        for (String seat : seats) {
            if (reservedSeats.contains(seat)) {
                return false; // One of the seats is already reserved
            }
        }

        // Reserve all selected seats
        for (String seat : seats) {
            reservedSeats.add(seat);

            // Save each reservation to the database
            Reservation reservation = new Reservation();
            reservation.setSeat(seat);
            reservation.setCustomerName(customerName);
            reservation.setMovieName(movieName);
            reservation.setEmail(email);

            reservationRepository.save(reservation);
        }
        return true; // Successfully reserved all seats
    }

    public Set<String> getReservedSeats() {
        return reservedSeats;
    }

    public String[][] getSeatLayout() {
        // Define a 10x10 hall (100 seats)
        String[][] layout = new String[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                String seat = (char) ('A' + row) + String.valueOf(col + 1);
                layout[row][col] = reservedSeats.contains(seat) ? "RESERVED" : "AVAILABLE";
            }
        }
        return layout;
    }
}

