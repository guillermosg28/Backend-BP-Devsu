package dev.guillermosg.msmovements.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msmovements.application.ports.input.ReporteUseCase;
import dev.guillermosg.msmovements.application.ports.output.ClienteOutputPort;
import dev.guillermosg.msmovements.application.ports.output.CuentaOutputPort;
import dev.guillermosg.msmovements.application.ports.output.MovimientoOutputPort;
import dev.guillermosg.msmovements.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ReporteService implements ReporteUseCase {

    private final ClienteOutputPort clienteOutputPort;
    private final CuentaOutputPort cuentaOutputPort;
    private final MovimientoOutputPort movimientoOutputPort;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * @param reporte
     */
    @Override
    public void generarEstadoCuenta(GenerarEstadoCuentaRequest reporte) {

        EstadoCuentaResponse estadoCuentaResponse = new EstadoCuentaResponse();
        List<Cuenta> cuentas = cuentaOutputPort.findByClienteId(reporte.getClienteId());

        List<CuentaResponse> cuentasResponse = new ArrayList<>();

        cuentas.forEach(cuenta -> {
            List<MovimientosResponse> movimientos = movimientoOutputPort
                    .findByCuentaIdAndFechaBetween(cuenta.getId(), reporte.getFechaInicio(), reporte.getFechaFin());

            movimientos.forEach(movimiento -> {
                movimiento.setDescripcion(movimiento.getTipoMovimiento() + " " + movimiento.getValor().abs());
            });

            CuentaResponse cuentaResponse = getCuentaResponse(cuenta, movimientos);
            cuentasResponse.add(cuentaResponse);
        });

        estadoCuentaResponse.setClienteId(reporte.getClienteId().toString());
        estadoCuentaResponse.setCliente(reporte.getCliente());
        estadoCuentaResponse.setCuentas(cuentasResponse);

        clienteOutputPort.publishReporteGenerado(estadoCuentaResponse);

    }

    private static CuentaResponse getCuentaResponse(Cuenta cuenta, List<MovimientosResponse> movimientos) {
        CuentaResponse cuentaResponse = new CuentaResponse();
        cuentaResponse.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaResponse.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaResponse.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaResponse.setSaldoActual(cuenta.getSaldoActual());
        cuentaResponse.setEstado(cuenta.getEstado());
        cuentaResponse.setClienteId(cuenta.getClienteId());
        cuentaResponse.setMovimientos(movimientos);
        return cuentaResponse;
    }
}
