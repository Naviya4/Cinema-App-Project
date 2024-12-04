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
}
