package com.consumer.kafka.infraestructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consumer.kafka.dominio.Balance;
import java.util.Optional;


@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    // Nenhuma implementação adicional é necessária, pois JpaRepository já fornece métodos CRUD padrão
    Optional<Balance> findByAccountId(String clientId);
}