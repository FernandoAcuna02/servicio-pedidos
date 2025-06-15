package com.ecommerce.pedidos.infrastructure.adapters.in.web.dto;

public record PedidoItemRequest(
    Long productoId,
    int cantidad
) {}