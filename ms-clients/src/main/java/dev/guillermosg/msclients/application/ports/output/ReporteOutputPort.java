package dev.guillermosg.msclients.application.ports.output;

import dev.guillermosg.msclients.domain.model.EstadoCuentaResponse;
import dev.guillermosg.msclients.domain.model.GenerarEstadoCuentaRequest;

import java.util.concurrent.CompletableFuture;

public interface ReporteOutputPort {

    CompletableFuture<EstadoCuentaResponse> generarEstadoCuenta(GenerarEstadoCuentaRequest reporte);

}
