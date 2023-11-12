package dev.guillermosg.msmovements.application.ports.input;

import dev.guillermosg.msmovements.domain.model.GenerarEstadoCuentaRequest;

/**
 * The interface Cuentas use case.
 */
public interface ReporteUseCase {

    void generarEstadoCuenta(GenerarEstadoCuentaRequest reporte);
}
