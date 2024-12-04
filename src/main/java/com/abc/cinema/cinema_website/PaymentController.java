package com.abc.cinema.cinema_website;

import com.abc.cinema.cinema_website.service.EmailService;
import com.abc.cinema.cinema_website.service.PayPalService;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PaymentController {

    @Autowired
    private PayPalService payPalService;

    @Autowired
    private EmailService emailService;

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

    @RequestMapping("/success")
    public String successPayment(@RequestParam Map<String, String> params, Model model) {
        String paymentId = params.get("paymentId");
        String payerId = params.get("PayerID");

        try {
            // Get the payment details and process the payment (as before)
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                model.addAttribute("message", "Payment Successful!");

                // Send a payment confirmation email after successful payment
                String customerEmail = "customer@example.com";  // Retrieve this from the customer details
                String paymentAmount = payment.getTransactions().get(0).getAmount().getTotal();
                emailService.sendPaymentConfirmation(customerEmail, paymentAmount);

                return "success";
            } else {
                model.addAttribute("message", "Payment not approved.");
                return "cancel";
            }
        } catch (PayPalRESTException e) {
            model.addAttribute("message", "Error while processing payment.");
            return "cancel";
        }
    }



    @GetMapping("/cancel")
    public String cancel(Model model) {
        model.addAttribute("message", "Payment Canceled.");
        return "cancel";
    }
}

