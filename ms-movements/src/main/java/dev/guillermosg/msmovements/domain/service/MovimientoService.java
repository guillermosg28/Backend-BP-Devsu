package dev.guillermosg.msmovements.domain.service;

import dev.guillermosg.msmovements.application.ports.input.MovimientoUseCase;
import dev.guillermosg.msmovements.application.ports.output.CuentaOutputPort;
import dev.guillermosg.msmovements.application.ports.output.MovimientoOutputPort;
import dev.guillermosg.msmovements.domain.model.MovimientoRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class MovimientoService implements MovimientoUseCase {

    private final MovimientoOutputPort movimientoOutputPort;
    private final CuentaOutputPort cuentaOutputPort;


    /**
     * @param request
     */
    @Override
    @Transactional
    public void save(MovimientoRequest request) {
        movimientoOutputPort.save(request);
        cuentaOutputPort.actualizarSaldo(request.getCuentaId(), request.getTipoMovimiento(), request.getValor());
    }
}
