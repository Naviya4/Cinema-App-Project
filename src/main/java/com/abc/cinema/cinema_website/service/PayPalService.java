package com.abc.cinema.cinema_website.service;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PayPalService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    public Payment createPayment(Double total, String currency, String method, String intent, String description, String cancelUrl, String successUrl) throws PayPalRESTException {
        // Set PayPal API credentials
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);

        // Set the payment details
        Amount amount = new Amount();
        amount.setTotal(String.format("%.2f", total));
        amount.setCurrency(currency);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(description);

        Payer payer = new Payer();
        payer.setPaymentMethod(method);

        Payment payment = new Payment();
        payment.setIntent(intent);
        payment.setPayer(payer);
        payment.setTransactions(java.util.Collections.singletonList(transaction));

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);

        payment.setRedirectUrls(redirectUrls);

        // Create payment
        Payment createdPayment = payment.create(apiContext);

        return createdPayment;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        // Set up the APIContext with your PayPal credentials
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");

        // Create a PaymentExecution instance to execute the payment
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        // Get the Payment by paymentId
        Payment payment = new Payment();
        payment.setId(paymentId);
        return payment.execute(apiContext, paymentExecution);
    }
}