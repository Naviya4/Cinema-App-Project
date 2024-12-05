package com.abc.cinema.cinema_website;


import com.abc.cinema.cinema_website.model.CustomerFeedback;
import com.abc.cinema.cinema_website.service.FeedbackService;
import com.abc.cinema.cinema_website.model.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedback")
    public String showFeedbackForm(Model model) {
        model.addAttribute("feedbackDTO", new FeedbackDTO());
        return "feedbackForm";  // This is the JSP page we created earlier
    }

    // Handle feedback submission
    @PostMapping("/submitFeedback")
    public String submitFeedback(@ModelAttribute FeedbackDTO feedbackDTO, Model model) {
        feedbackService.saveFeedback(feedbackDTO);
        model.addAttribute("message", "Thank you for your feedback!");
        return "feedbackSuccess";
    }

    // Display all feedback in the dashboard
    @GetMapping("/feedbackDashboard")
    public String feedbackDashboard(Model model) {
        List<CustomerFeedback> feedbackList = feedbackService.getAllFeedback();
        model.addAttribute("feedbackList", feedbackList);
        return "feedbackDashboard";
    }

}
