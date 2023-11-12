package dev.guillermosg.msmovements.application.ports.output;

import dev.guillermosg.msmovements.domain.model.Cuenta;
import dev.guillermosg.msmovements.domain.model.CuentaRequest;
import dev.guillermosg.msmovements.domain.model.UpdateStatusAccountRequest;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity.CuentaEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Cuenta output port.
 */
public interface CuentaOutputPort {

    void save(CuentaRequest cuenta);

    Optional<CuentaEntity> findById(Integer cuentaId);

    List<Cuenta> findByClienteId(Integer clienteId);

    void actualizarSaldo(Integer cuentaId, String tipoMovimiento, BigDecimal valor);

    List<Cuenta> findAll();

    void updateStatusAccount(UpdateStatusAccountRequest request);

}
