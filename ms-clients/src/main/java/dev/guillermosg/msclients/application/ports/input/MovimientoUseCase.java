package dev.guillermosg.msclients.application.ports.input;

import dev.guillermosg.msclients.domain.model.MovimientoRequest;
import dev.guillermosg.msclients.domain.model.SuccessResponse;

/**
 * The interface Movimientos use case.
 */
public interface MovimientoUseCase {

    SuccessResponse createMovement(MovimientoRequest movimiento);
}
