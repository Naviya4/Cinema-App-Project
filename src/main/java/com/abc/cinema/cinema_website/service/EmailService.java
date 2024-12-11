package com.abc.cinema.cinema_website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendReservationConfirmation(String toEmail, String movie, String seat) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("ABC Cinema Reservation Confirmation");
        message.setText("Your reservation for movie " + movie + " and seat " + seat + " is confirmed.");
        mailSender.send(message);
    }

    // New method for sending payment confirmation
    public void sendPaymentConfirmation(String toEmail, String paymentAmount) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("ABC Cinema Payment Confirmation");
        message.setText("Dear Customer,\n\nYour payment of $" + paymentAmount + " was successfully processed.\n\nThank you for your reservation!");
        mailSender.send(message);
    }

    public void sendPaymentAndReservationConfirmation(String customerEmail, String paymentAmount, String movie, String reservedSeats, String customerName) {
        // Construct the email content
        String subject = "Payment and Reservation Confirmation";
        String body = "Dear " + (customerName != null ? customerName : "Customer") + ",\n\n"
                + "Your payment of $" + paymentAmount + " for the movie '" + movie + "' was successful.\n"
                + "The following seats have been reserved for you: " + reservedSeats + ".\n\n"
                + "Thank you for your purchase!\n\n"
                + "Best regards,\nYour Movie Reservation Team";

        try {
            // Use your email service to send the email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(customerEmail);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            // Log success
            System.out.println("Confirmation email sent successfully to " + customerEmail);
        } catch (Exception e) {
            // Log failure
            System.err.println("Failed to send confirmation email to " + customerEmail);
            e.printStackTrace();
        }
    }

}