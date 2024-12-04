package com.abc.cinema.cinema_website.service;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ReservationService {

    private final Set<String> reservedSeats = new HashSet<>();

    public synchronized boolean reserveSeat(String seat) {
        if (reservedSeats.contains(seat)) {
            return false; // Seat is already booked
        }
        reservedSeats.add(seat);
        return true; // Successfully reserved
    }

    public Set<String> getReservedSeats() {
        return reservedSeats;
    }
}
