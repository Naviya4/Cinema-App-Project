package com.abc.cinema.cinema_website.service;

import com.abc.cinema.cinema_website.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    private com.abc.cinema.cinema_website.repo.ReservationRepository reservationRepository;

    // Map to store reserved seats by movie name
    private final Map<String, Set<String>> reservedSeatsByMovie = new HashMap<>();

    // Updated to handle multiple seat reservations for specific movies
    public synchronized boolean reserveSeats(List<String> seats, String customerName, String movieName, String email) {
        // Get the reserved seats for the given movie or initialize a new set if none exist
        Set<String> reservedSeats = reservedSeatsByMovie.getOrDefault(movieName, new HashSet<>());

        // Check if any of the selected seats are already reserved for the given movie
        for (String seat : seats) {
            if (reservedSeats.contains(seat)) {
                return false; // One of the seats is already reserved for this movie
            }
        }

        // Reserve all selected seats for the movie
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

        // Update the reserved seats map for the movie
        reservedSeatsByMovie.put(movieName, reservedSeats);

        return true; // Successfully reserved all seats for the movie
    }

    public Set<String> getReservedSeatsForMovie(String movieName) {
        return reservedSeatsByMovie.getOrDefault(movieName, new HashSet<>());
    }

    public String[][] getSeatLayout(String movieName) {
        // Define a 10x10 hall (100 seats)
        String[][] layout = new String[10][10];

        // Get the reserved seats for the specific movie
        Set<String> reservedSeats = reservedSeatsByMovie.getOrDefault(movieName, new HashSet<>());

        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                String seat = (char) ('A' + row) + String.valueOf(col + 1);
                layout[row][col] = reservedSeats.contains(seat) ? "RESERVED" : "AVAILABLE";
            }
        }
        return layout;
    }

    public boolean checkSeatsAvailability(List<String> seats, String movieName) {
        // Get the reserved seats for the movie
        Set<String> reservedSeats = reservedSeatsByMovie.getOrDefault(movieName, new HashSet<>());

        // Check if any of the selected seats are already reserved
        for (String seat : seats) {
            if (reservedSeats.contains(seat)) {
                return false; // One of the seats is already reserved for this movie
            }
        }
        return true; // All seats are available for this movie
    }
}