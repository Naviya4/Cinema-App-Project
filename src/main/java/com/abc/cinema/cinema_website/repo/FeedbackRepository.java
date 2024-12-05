package com.abc.cinema.cinema_website.repo;


import com.abc.cinema.cinema_website.model.CustomerFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<CustomerFeedback, Long> {
    // We can add custom query methods if needed
}

