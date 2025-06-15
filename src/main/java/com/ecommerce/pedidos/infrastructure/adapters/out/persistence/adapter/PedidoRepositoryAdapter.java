package com.ecommerce.pedidos.infrastructure.adapters.out.persistence.adapter;

import com.ecommerce.pedidos.domain.model.Pedido;
import com.ecommerce.pedidos.domain.model.PedidoItem;
import com.ecommerce.pedidos.domain.port.out.PedidoRepositoryPort;
import com.ecommerce.pedidos.infrastructure.adapters.out.persistence.entity.PedidoEntity;
import com.ecommerce.pedidos.infrastructure.adapters.out.persistence.entity.PedidoItemEntity;
import com.ecommerce.pedidos.infrastructure.adapters.out.persistence.repository.PedidoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository pedidoJpaRepository;

    public PedidoRepositoryAdapter(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    // Mapeador para convertir un item de Dominio a Entidad
    private PedidoItemEntity toItemEntity(PedidoItem item) {
        PedidoItemEntity entity = new PedidoItemEntity();
        entity.setId(item.getId());
        entity.setProductoId(item.getProductoId());
        entity.setCantidad(item.getCantidad());
        entity.setPrecioUnitario(item.getPrecioUnitario());
        return entity;
    }

    // ESTE ES EL MÉTODO QUE FALTABA O ESTABA INCORRECTO
    @Override
    public Pedido save(Pedido pedido) {
        // 1. Mapear el Pedido de dominio a una Entidad de persistencia
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setUsuarioId(pedido.getUsuarioId());
        pedidoEntity.setFecha(pedido.getFecha());
        pedidoEntity.setEstado(pedido.getEstado());
        pedidoEntity.setTotal(pedido.getTotal());
        // Mapeamos también la lista de items
        pedidoEntity.setItems(pedido.getItems().stream()
            .map(this::toItemEntity)
            .collect(Collectors.toList()));

        // 2. Usar el repositorio de Spring Data JPA para guardar en la BD
        PedidoEntity savedEntity = pedidoJpaRepository.save(pedidoEntity);

        // 3. Devolvemos el pedido con el ID asignado por la BD (mapeado de vuelta al dominio)
        pedido.setId(savedEntity.getId());
        return pedido;
    }
}