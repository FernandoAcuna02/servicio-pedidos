package com.ecommerce.pedidos.domain.port.out;

import com.ecommerce.pedidos.domain.model.Producto;
import java.util.Optional;

public interface ProductoClientPort {
    Optional<Producto> obtenerProducto(Long id);
}