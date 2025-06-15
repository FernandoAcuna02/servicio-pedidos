package com.ecommerce.pedidos.domain.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Pedido {
    private Long id;
    private Long usuarioId;
    private LocalDateTime fecha;
    private String estado;
    private List<PedidoItem> items;
    private BigDecimal total;
}