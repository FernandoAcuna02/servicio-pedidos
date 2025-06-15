package com.ecommerce.pedidos.infrastructure.adapters.in.web;

import com.ecommerce.pedidos.domain.model.Pedido;
import com.ecommerce.pedidos.domain.model.PedidoItem;
import com.ecommerce.pedidos.domain.port.in.PedidoServicePort;
import com.ecommerce.pedidos.infrastructure.adapters.in.web.dto.PedidoRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoServicePort pedidoServicePort;

    public PedidoController(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody PedidoRequest pedidoRequest) {
        // Mapeamos del DTO al modelo de Dominio
        Pedido pedido = new Pedido();
        pedido.setUsuarioId(pedidoRequest.usuarioId());
        pedido.setItems(pedidoRequest.items().stream().map(itemReq -> {
            PedidoItem item = new PedidoItem();
            item.setProductoId(itemReq.productoId());
            item.setCantidad(itemReq.cantidad());
            return item;
        }).collect(Collectors.toList()));

        Pedido pedidoCreado = pedidoServicePort.crearPedido(pedido);
        return new ResponseEntity<>(pedidoCreado, HttpStatus.CREATED);
    }
}