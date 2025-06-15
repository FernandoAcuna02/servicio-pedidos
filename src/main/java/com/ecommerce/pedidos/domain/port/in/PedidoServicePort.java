package com.ecommerce.pedidos.domain.port.in;

import com.ecommerce.pedidos.domain.model.Pedido;

public interface PedidoServicePort {
    Pedido crearPedido(Pedido pedido);
}