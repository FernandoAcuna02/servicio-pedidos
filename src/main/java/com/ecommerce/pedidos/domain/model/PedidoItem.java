package com.ecommerce.pedidos.domain.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PedidoItem {
    private Long id;
    private Long productoId;
    private int cantidad;
    private BigDecimal precioUnitario; // Este precio lo obtendremos del servicio de productos
}