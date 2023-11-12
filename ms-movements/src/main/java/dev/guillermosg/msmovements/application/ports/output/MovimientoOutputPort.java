package dev.guillermosg.msmovements.application.ports.output;

import dev.guillermosg.msmovements.domain.model.MovimientoRequest;
import dev.guillermosg.msmovements.domain.model.MovimientosResponse;

import java.util.List;

/**
 * The interface Cuentas output port.
 */
public interface MovimientoOutputPort {

    void save(MovimientoRequest movimiento);

    List<MovimientosResponse> findByCuentaIdAndFechaBetween(Long cuentaId, String fechaInicio, String fechaFin);
}
