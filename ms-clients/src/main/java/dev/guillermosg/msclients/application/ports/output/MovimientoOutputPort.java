package dev.guillermosg.msclients.application.ports.output;

import dev.guillermosg.msclients.domain.model.MovimientoRequest;

public interface MovimientoOutputPort {

    void publishSave(MovimientoRequest movimiento);

}
