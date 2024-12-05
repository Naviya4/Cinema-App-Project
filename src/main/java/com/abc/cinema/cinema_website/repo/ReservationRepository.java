package com.abc.cinema.cinema_website.repo;

import com.abc.cinema.cinema_website.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // You can add custom query methods here if needed
}