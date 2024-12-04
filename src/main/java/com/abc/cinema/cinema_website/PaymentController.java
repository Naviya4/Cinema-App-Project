package com.abc.cinema.cinema_website;

import com.abc.cinema.cinema_website.service.PayPalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {

    @Autowired
    private PayPalService payPalService;

    @GetMapping("/pay")
    public String pay(Model model) throws PayPalRESTException {
        // Details for payment
        Double totalAmount = 100.00;  // Example amount
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

    @GetMapping("/success")
    public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, Model model) {
        // Handle successful payment
        // For simplicity, you can confirm payment here.
        model.addAttribute("message", "Payment Successful! Payment ID: " + paymentId);
        return "success";
    }

    @GetMapping("/cancel")
    public String cancel(Model model) {
        model.addAttribute("message", "Payment Canceled.");
        return "cancel";
    }
}

