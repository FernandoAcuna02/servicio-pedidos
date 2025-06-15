package com.ecommerce.pedidos.infrastructure.adapters.in.web.dto;

import java.util.List;

public record PedidoRequest(
    Long usuarioId,
    List<PedidoItemRequest> items
) {}