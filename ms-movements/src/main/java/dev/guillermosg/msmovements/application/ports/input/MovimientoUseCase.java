package dev.guillermosg.msmovements.application.ports.input;

import dev.guillermosg.msmovements.domain.model.MovimientoRequest;

public interface MovimientoUseCase {

    void save(MovimientoRequest request);
}
