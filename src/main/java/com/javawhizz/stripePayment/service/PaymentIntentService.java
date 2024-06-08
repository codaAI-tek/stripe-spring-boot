package com.javawhizz.stripePayment.service;
import com.javawhizz.stripePayment.Repository.PaymentRepository;
import com.javawhizz.stripePayment.model.Request;
import com.javawhizz.stripePayment.model.Response;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.javawhizz.stripePayment.model.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentIntentService {
	  private final PaymentRepository paymentRepository;
	    private static final Logger logger = LoggerFactory.getLogger(PaymentIntentService.class);

	  @Autowired
	    public PaymentIntentService(PaymentRepository paymentRepository) {
	        this.paymentRepository = paymentRepository;
	    }
	  public Response createPaymentIntent(Request request) throws StripeException {
	        PaymentIntentCreateParams params =
	                PaymentIntentCreateParams.builder()
	                        .setAmount(request.getAmount() * 100L)
	                        .putMetadata("productName", request.getProductName())
	                        .setCurrency("usd")
	                        .setAutomaticPaymentMethods(
	                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
	                                        .setEnabled(true)
	                                        .build()
	                        )
	                        .build();

	        PaymentIntent intent = PaymentIntent.create(params);

	        Payment payment = new Payment();
	        payment.setIntentID(intent.getId());
	        payment.setClientSecret(intent.getClientSecret());
	        payment.setStatus(intent.getStatus());
	        payment.setAmount(request.getAmount());
	     
	        paymentRepository.save(payment);

	        try {
	            paymentRepository.save(payment);
	            logger.info("Payment inserted successfully: {}", payment);
	            return new Response(intent.getId(), intent.getClientSecret(), intent.getStatus(), "Success");
	        } catch (Exception e) {
	            logger.error("Error inserting payment", e);
	            return new Response(intent.getId(), intent.getClientSecret(), intent.getStatus(), "Failure: " + e.getMessage());
	        }
	    }
	  public Response getPaymentStatus(String intentId) {
	        try {
	            PaymentIntent intent = PaymentIntent.retrieve(intentId);
	            return new Response(intent.getId(), intent.getClientSecret(), intent.getStatus(), "Success");
	        } catch (StripeException e) {
	            logger.error("Error retrieving payment status for ID: {}", intentId, e);
	            return new Response(intentId, null, null, "Failure: " + e.getMessage());
	        }
	    }
}