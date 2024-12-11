package com.abc.cinema.cinema_website;

import com.abc.cinema.cinema_website.service.EmailService;
import com.abc.cinema.cinema_website.service.PayPalService;
import com.abc.cinema.cinema_website.service.ReservationService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class PaymentController {

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ReservationService reservationService;


    @GetMapping("/pay")
    public String pay(HttpSession session, Model model) throws PayPalRESTException {

        // Retrieve data from the session
        String movie = (String) session.getAttribute("reservedMovie");
        List<String> seats = (List<String>) session.getAttribute("reservedSeats");
        String customerName = (String) session.getAttribute("customerName");
        String customerEmail = (String) session.getAttribute("customerEmail");

        // Check if all reservation details are available
        if (movie == null || seats == null || customerName == null || customerEmail == null) {
            model.addAttribute("message", "Error: Missing reservation details. Please try again.");
            return "cancel"; // Forward to cancel.jsp
        }

        // Details for payment
        Double totalAmount = 5.00 * seats.size();  // Example amount
        String currency = "USD";
        String method = "paypal";
        String intent = "sale";
        String description = "Cinema Ticket Reservation";

        // URLs for PayPal redirection
        String cancelUrl = "http://localhost:8080/cancel";
        String successUrl = "http://localhost:8080/success";

        // Create the payment
        Payment payment = payPalService.createPayment(totalAmount, currency, method, intent, description, cancelUrl, successUrl);

        // Add payment approval URL to the model for redirection
        model.addAttribute("approvalUrl", payment.getLinks().get(1).getHref()); // Approval URL for the user

        return "redirect:" + payment.getLinks().get(1).getHref();
    }

    @RequestMapping("/success")
    public String successPayment(@RequestParam Map<String, String> params, HttpSession session, Model model) {
        String paymentId = params.get("paymentId");
        String payerId = params.get("PayerID");

        try {
            // Get the payment details and process the payment
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                model.addAttribute("message", "Payment Successful!");

                // Retrieve customer details, movie name, and selected seats from session
                String movie = (String) session.getAttribute("reservedMovie");
                List<String> seats = (List<String>) session.getAttribute("reservedSeats");
                String customerName = (String) session.getAttribute("customerName");
                String customerEmail = (String) session.getAttribute("customerEmail");

                // Validate if reservation details are available
                if (customerEmail == null || customerName == null || movie == null || seats == null) {
                    model.addAttribute("message", "Error: Missing reservation details. Please try again.");
                    return "cancel"; // Forward to cancel.jsp
                }

                // Call reserveSeats to reserve the selected seats for the specific movie
                boolean seatsReserved = reservationService.reserveSeats(seats, customerName, movie, customerEmail);
                if (seatsReserved) {
                    // Send a payment and reservation confirmation email
                    String paymentAmount = payment.getTransactions().get(0).getAmount().getTotal();
                    emailService.sendPaymentAndReservationConfirmation(customerEmail, paymentAmount, movie, String.join(", ", seats), customerName);

                    return "success"; // Forward to success.jsp
                } else {
                    model.addAttribute("message", "Error: Unable to reserve seats.");
                    return "cancel"; // Forward to cancel.jsp
                }

            } else {
                model.addAttribute("message", "Payment not approved.");
                return "cancel"; // Forward to cancel.jsp
            }
        } catch (PayPalRESTException e) {
            model.addAttribute("message", "Error while processing payment.");
            return "cancel"; // Forward to cancel.jsp
        }
    }

    @GetMapping("/cancel")
    public String cancel(Model model) {
        model.addAttribute("message", "Payment Canceled.");
        return "cancel";
    }
}