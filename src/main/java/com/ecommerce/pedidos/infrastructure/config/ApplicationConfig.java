package com.ecommerce.pedidos.infrastructure.config;

import com.ecommerce.pedidos.application.usecase.PedidoServiceImpl;
import com.ecommerce.pedidos.domain.port.in.PedidoServicePort;
import com.ecommerce.pedidos.domain.port.out.PedidoRepositoryPort;
import com.ecommerce.pedidos.domain.port.out.ProductoClientPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PedidoServicePort pedidoServicePort(PedidoRepositoryPort pedidoRepositoryPort, ProductoClientPort productoClientPort) {
        // Spring inyectará automáticamente el PedidoRepositoryAdapter y el ProductoRestClientAdapter
        return new PedidoServiceImpl(pedidoRepositoryPort, productoClientPort);
    }
}