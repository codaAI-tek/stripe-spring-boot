package com.javawhizz.stripePayment.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @NotNull
    @Min(1)
    private Long amount;

    @Email
    private String email;

    @NotBlank
    @Size(min = 1, max = 200)
    private String productName;
}
