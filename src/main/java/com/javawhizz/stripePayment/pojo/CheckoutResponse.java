package com.javawhizz.stripePayment.pojo;

public class CheckoutResponse {
    private String publicKey;
    private Long amount;
    private String email;
    private String productName;

    public CheckoutResponse() {
    }

    public CheckoutResponse(String publicKey, Long amount, String email, String productName) {
        this.publicKey = publicKey;
        this.amount = amount;
        this.email = email;
        this.productName = productName;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}