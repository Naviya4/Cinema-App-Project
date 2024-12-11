package com.abc.cinema.cinema_website.service;

import com.abc.cinema.cinema_website.model.CustomerFeedback;
import com.abc.cinema.cinema_website.model.FeedbackDTO;
import com.abc.cinema.cinema_website.repo.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Save feedback to the database
    public void saveFeedback(FeedbackDTO feedbackDTO) {
        CustomerFeedback feedback = new CustomerFeedback();
        feedback.setCustomer(feedbackDTO.getCustomer());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setComments(feedbackDTO.getComment());

        feedbackRepository.save(feedback);
    }

    // Fetch all feedback from the database
    public List<CustomerFeedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}