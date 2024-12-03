package com.abc.cinema.cinema_website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

    @PostMapping("/reserve")
    public String reserveTicket(@RequestParam("movie") String movie,
                                @RequestParam("seat") String seat,
                                Model model) {
        model.addAttribute("movie", movie);
        model.addAttribute("seat", seat);
        return "reservation";  // Forward to reservation.jsp
    }


}
