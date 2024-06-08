package com.javawhizz.stripePayment.controller;

import com.javawhizz.stripePayment.model.Request;
import com.javawhizz.stripePayment.model.Response;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javawhizz.stripePayment.service.PaymentIntentService;
@RestController
public class PaymentIntentController {
	 private final PaymentIntentService paymentIntentService;
    private static final String AUTH_TOKEN = "123"; // Mock token for demonstration purposes

     @Autowired
     public PaymentIntentController(PaymentIntentService paymentIntentService) {
         this.paymentIntentService = paymentIntentService;
     }
     @Operation(summary = "Get Payment Status", description = "Get the status of a payment intent")
     @ApiResponses(value = {
         @ApiResponse(responseCode = "200", description = "Found the payment status",
             content = { @Content(mediaType = "application/json",
             schema = @Schema(implementation = Response.class)) }),
         @ApiResponse(responseCode = "404", description = "Payment intent not found",
             content = @Content) })
     @GetMapping("/status/{intentId}")
     @ResponseBody 
     public Response getPaymentStatus(@PathVariable String intentId) {
         return paymentIntentService.getPaymentStatus(intentId);
     }
     @Operation(summary = "Create Payment Intent", description = "Create a new payment intent",
    	        security = @SecurityRequirement(name = "bearerAuth"))
    	    @ApiResponses(value = {
    	        @ApiResponse(responseCode = "200", description = "Payment intent created",
    	            content = { @Content(mediaType = "application/json",
    	            schema = @Schema(implementation = Response.class)) }),
    	        @ApiResponse(responseCode = "401", description = "Unauthorized",
    	            content = @Content) })
     @PostMapping("/create-payment-intent")
     @ResponseBody 
     public Response createPaymentIntent(@RequestHeader("token") String token, @RequestBody Request request) throws StripeException {
         if (!token.equals(AUTH_TOKEN)) {
             return new Response(null, null, null, "Unauthorized: Invalid token");
         }
         return paymentIntentService.createPaymentIntent(request);
     }
}
