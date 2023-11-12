package dev.guillermosg.msclients.application.ports.input;

import dev.guillermosg.msclients.domain.model.EstadoCuentaResponse;

/**
 * The interface Reporte use case.
 */
public interface ReporteUseCase {

    EstadoCuentaResponse estadoCuenta(String fechaInicio, String fechaFin, Integer clienteId);
}
