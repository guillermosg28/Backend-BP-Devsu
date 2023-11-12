package dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.repository;

import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Movimiento repository.
 */

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

    List<MovimientoEntity> findByCuentaIdAndFechaBetween(Long cuentaId, String fechaInicio, String fechaFin);
}
