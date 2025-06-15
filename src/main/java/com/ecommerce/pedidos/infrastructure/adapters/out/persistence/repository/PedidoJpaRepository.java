package com.ecommerce.pedidos.infrastructure.adapters.out.persistence.repository;

import com.ecommerce.pedidos.infrastructure.adapters.out.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {
    // No necesitamos métodos adicionales por ahora, JpaRepository nos da todo lo básico.
}