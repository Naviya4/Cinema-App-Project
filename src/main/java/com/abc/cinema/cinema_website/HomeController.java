package com.abc.cinema.cinema_website;

import com.abc.cinema.cinema_website.model.Movie;
import com.abc.cinema.cinema_website.service.EmailService;
import com.abc.cinema.cinema_website.service.MovieService;
import com.abc.cinema.cinema_website.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String home(Model model) {
        // Fetch the list of showing movies
        List<Movie> showingMovies = movieService.getAllMovies();
        model.addAttribute("movies", showingMovies);
        return "index"; // Show movies on the home page
    }

    @RequestMapping("/reservationForm")
    public ModelAndView showReservationForm(@RequestParam("movie") String movie) {
        ModelAndView modelAndView = new ModelAndView("reservationForm");
        modelAndView.addObject("selectedMovie", movie); // Pass selected movie to the form

        // Add seat layout to the model
        String[][] seatLayout = reservationService.getSeatLayout(movie);
        modelAndView.addObject("seatLayout", seatLayout);

        return modelAndView;
    }

    @PostMapping("/reserve")
    public String reserveTicket(@RequestParam("movie") String movie,
                                @RequestParam("seats") List<String> seats,  // Accept a list of selected seats
                                @RequestParam("email") String email,
                                @RequestParam("customerName") String customerName,
                                HttpSession session,
                                Model model) {

        // Save data in the session
        session.setAttribute("reservedMovie", movie);
        session.setAttribute("reservedSeats", seats);
        session.setAttribute("customerName", customerName);
        session.setAttribute("customerEmail", email);

        // Just check if the seats are available, without reserving them or sending emails
        boolean areSeatsAvailable = reservationService.checkSeatsAvailability(seats, movie);

        if (areSeatsAvailable) {
            model.addAttribute("movie", movie);
            model.addAttribute("seats", seats);  // Pass the selected seats to the view
            return "reservation";  // Forward to reservation.jsp
        } else {
            model.addAttribute("error", "One or more selected seats are already reserved.");
            return "error";  // Forward to error.jsp
        }
    }

}