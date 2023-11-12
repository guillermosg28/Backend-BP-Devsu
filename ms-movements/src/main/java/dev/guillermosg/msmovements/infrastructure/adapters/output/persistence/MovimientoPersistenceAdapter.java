package dev.guillermosg.msmovements.infrastructure.adapters.output.persistence;

import dev.guillermosg.msmovements.application.ports.output.MovimientoOutputPort;
import dev.guillermosg.msmovements.domain.model.MovimientoRequest;
import dev.guillermosg.msmovements.domain.model.MovimientosResponse;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.mapper.MovimientoPersistenceMapper;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovimientoPersistenceAdapter implements MovimientoOutputPort {

    private final MovimientoPersistenceMapper mapper;
    private final MovimientoRepository movimientoRepository;

    /**
     * @param movimiento
     */
    @Override
    public void save(MovimientoRequest movimiento) {
        movimientoRepository.save(mapper.toMovimiento(movimiento));

    }

    /**
     * @param cuentaId
     * @param fechaInicio
     * @param fechaFin
     * @return List<MovimientosResponse>
     */
    @Override
    public List<MovimientosResponse> findByCuentaIdAndFechaBetween(Long cuentaId, String fechaInicio, String fechaFin) {
        return movimientoRepository
                .findByCuentaIdAndFechaBetween(cuentaId, fechaInicio, fechaFin)
                .stream().map(mapper::toMovimientosResponse).collect(Collectors.toList());
    }
}
