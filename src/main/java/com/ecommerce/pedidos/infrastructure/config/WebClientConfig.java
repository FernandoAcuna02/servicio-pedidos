package com.ecommerce.pedidos.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        // Configuramos el WebClient con la URL base de nuestro servicio de productos
        return WebClient.builder()
                .baseUrl("http://localhost:8081/api/productos")
                .build();
    }
}