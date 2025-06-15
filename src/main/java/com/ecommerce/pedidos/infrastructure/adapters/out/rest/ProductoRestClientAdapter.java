package com.ecommerce.pedidos.infrastructure.adapters.out.rest;

import com.ecommerce.pedidos.domain.model.Producto;
import com.ecommerce.pedidos.domain.port.out.ProductoClientPort;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component // Marcamos esta clase como un componente de Spring
public class ProductoRestClientAdapter implements ProductoClientPort {

    private final WebClient webClient;

    // Inyectamos el WebClient que configuramos antes
    public ProductoRestClientAdapter(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<Producto> obtenerProducto(Long id) {
        try {
            // Hacemos la llamada GET al servicio de productos
            Producto producto = webClient.get()
                    .uri("/{id}", id) // Añadimos el ID a la URL base
                    .retrieve() // Ejecutamos la petición
                    .bodyToMono(Producto.class) // Convertimos el cuerpo de la respuesta a un objeto Producto
                    .block(); // Esperamos de forma síncrona la respuesta (simple para este caso)

            return Optional.ofNullable(producto);
        } catch (Exception e) {
            // En un caso real, aquí manejaríamos mejor los errores (ej. si el servicio no responde)
            System.err.println("Error al llamar al servicio de productos: " + e.getMessage());
            return Optional.empty();
        }
    }
}