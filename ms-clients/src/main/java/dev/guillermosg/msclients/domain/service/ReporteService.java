package dev.guillermosg.msclients.domain.service;

import dev.guillermosg.msclients.application.ports.input.ReporteUseCase;
import dev.guillermosg.msclients.application.ports.output.ClienteOutputPort;
import dev.guillermosg.msclients.application.ports.output.ReporteOutputPort;
import dev.guillermosg.msclients.domain.exception.ClientNotExistException;
import dev.guillermosg.msclients.domain.model.EstadoCuentaResponse;
import dev.guillermosg.msclients.domain.model.GenerarEstadoCuentaRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class ReporteService implements ReporteUseCase {

    private final ReporteOutputPort reporteOutputPort;
    private final ClienteOutputPort clienteOutputPort;

    /**
     * @param fechaInicio
     * @param fechaFin
     * @param clienteId
     * @return EstadoCuentaResponse
     */
    @Override
    public EstadoCuentaResponse estadoCuenta(String fechaInicio, String fechaFin, Integer clienteId){

        var cliente = clienteOutputPort.findByClienteId(clienteId).orElseThrow(ClientNotExistException::new);

        GenerarEstadoCuentaRequest generarEstadoCuentaRequest = new GenerarEstadoCuentaRequest();
        generarEstadoCuentaRequest.setFechaInicio(fechaInicio);
        generarEstadoCuentaRequest.setFechaFin(fechaFin);
        generarEstadoCuentaRequest.setClienteId(clienteId);
        generarEstadoCuentaRequest.setCliente(cliente.getNombre());

        return generarEstadoCuenta(generarEstadoCuentaRequest);
    }

    private EstadoCuentaResponse generarEstadoCuenta(GenerarEstadoCuentaRequest reporte) {
        CompletableFuture<EstadoCuentaResponse> estadoCuenta = reporteOutputPort.generarEstadoCuenta(reporte);
        CompletableFuture<EstadoCuentaResponse> estadoCuentaFuture = estadoCuenta
                .thenApplyAsync(estadoCuentaResponse -> estadoCuentaResponse);
        return estadoCuentaFuture.join();
    }
}
