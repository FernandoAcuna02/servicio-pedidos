package com.ecommerce.pedidos.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "pedido_items")
@Data
public class PedidoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productoId;
    private int cantidad;
    private BigDecimal precioUnitario;
}