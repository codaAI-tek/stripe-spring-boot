package com.javawhizz.stripePayment.controller;

import com.javawhizz.stripePayment.model.Request;
import com.javawhizz.stripePayment.pojo.CheckoutResponse;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
@RestController
@Tag(name = "Payment API", description = "Payment related operations")
public class AppController {
    @Value("${stripe.api.publicKey}")
    private String publicKey;
    
    @Operation(summary = "Show Card", description = "Displays the card details for payment")
    @PostMapping("/")
    public CheckoutResponse showCard(@RequestBody @Valid Request request, BindingResult bindingResult) {
        CheckoutResponse response = new CheckoutResponse();
        
            // 设置响应数据
            response.setPublicKey(publicKey);
            response.setAmount(request.getAmount());
            response.setEmail(request.getEmail());
            response.setProductName(request.getProductName());
         
        return response;
    }
}
