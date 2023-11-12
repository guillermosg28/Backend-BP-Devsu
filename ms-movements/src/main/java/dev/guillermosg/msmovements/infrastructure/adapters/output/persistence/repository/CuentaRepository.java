package dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.repository;

import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Cuenta repository.
 */

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

    List<CuentaEntity> findByClienteId(Integer clienteId);

}
