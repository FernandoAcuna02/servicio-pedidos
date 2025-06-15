package com.ecommerce.pedidos.domain.port.out;

import com.ecommerce.pedidos.domain.model.Pedido;

public interface PedidoRepositoryPort {
    Pedido save(Pedido pedido);
}