package dev.guillermosg.msclients.infrastructure.adapters.input.rest;

import dev.guillermosg.msclients.application.ports.input.ReporteUseCase;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.EstadoCuentaResponseDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper.ReporteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Reportes rest adapter.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class ReportesRestAdapter implements ReportesApi{

    private final ReporteMapper reporteMapper;
    private final ReporteUseCase reporteUseCase;
    /**
     * @param fechaInicio Fecha inicio (required)
     * @param FechaFin    Fecha fin (required)
     * @param clienteId   Cliente id (required)
     * @return EstadoCuentaResponseDto
     */
    @Override
    public ResponseEntity<EstadoCuentaResponseDto> _estadoCuenta(String fechaInicio, String FechaFin, Integer clienteId) {
        return ResponseEntity.ok().body(reporteMapper
                .estadoCuentaToDto(reporteUseCase.estadoCuenta(fechaInicio, FechaFin, clienteId)));
    }
}
