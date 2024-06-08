package com.javawhizz.stripePayment.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig  {
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Stripe Payment API")
            .version("1.0")
            .description("API documentation for Stripe Payment")
            .license(new License().name("Apache 2.0").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
            .description("Stripe Payment Documentation")
            .url("https://example.com/docs"));
    }
}