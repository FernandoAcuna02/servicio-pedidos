package com.ecommerce.pedidos.application.usecase;

import com.ecommerce.pedidos.domain.model.Pedido;
import com.ecommerce.pedidos.domain.model.PedidoItem;
import com.ecommerce.pedidos.domain.model.Producto;
import com.ecommerce.pedidos.domain.port.in.PedidoServicePort;
import com.ecommerce.pedidos.domain.port.out.PedidoRepositoryPort;
import com.ecommerce.pedidos.domain.port.out.ProductoClientPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoServiceImpl implements PedidoServicePort {

    private final PedidoRepositoryPort pedidoRepositoryPort;
    private final ProductoClientPort productoClientPort;

    public PedidoServiceImpl(PedidoRepositoryPort pedidoRepositoryPort, ProductoClientPort productoClientPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
        this.productoClientPort = productoClientPort;
    }

    @Override
    public Pedido crearPedido(Pedido pedido) {
        // 1. Obtener información de los productos desde el servicio de productos
        List<PedidoItem> itemsProcesados = pedido.getItems().stream().map(item -> {
            Producto productoInfo = productoClientPort.obtenerProducto(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + item.getProductoId()));

            if (productoInfo.getStock() < item.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + productoInfo.getNombre());
            }

            item.setPrecioUnitario(productoInfo.getPrecio());
            return item;
        }).collect(Collectors.toList());

        // 2. Calcular el total
        BigDecimal total = itemsProcesados.stream()
                .map(item -> item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 3. Completar los datos del pedido
        pedido.setItems(itemsProcesados);
        pedido.setTotal(total);
        pedido.setFecha(LocalDateTime.now());
        pedido.setEstado("CREADO");

        // 4. Guardar en la base de datos
        return pedidoRepositoryPort.save(pedido);
    }
}