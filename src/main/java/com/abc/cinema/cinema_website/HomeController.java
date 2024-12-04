package com.abc.cinema.cinema_website;

import com.abc.cinema.cinema_website.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/reserve")
    public String reserveTicket(@RequestParam("movie") String movie,
                                @RequestParam("seat") String seat,
                                Model model) {
        if (reservationService.reserveSeat(seat)) {
            model.addAttribute("movie", movie);
            model.addAttribute("seat", seat);
            return "reservation";  // Forward to reservation.jsp
        } else {
            model.addAttribute("error", "Seat " + seat + " is already reserved.");
            return "error";  // Forward to error.jsp
        }
    }

}