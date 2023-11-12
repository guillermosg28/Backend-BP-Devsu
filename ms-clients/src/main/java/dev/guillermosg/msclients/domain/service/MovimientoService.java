package dev.guillermosg.msclients.domain.service;

import dev.guillermosg.msclients.application.ports.input.MovimientoUseCase;
import dev.guillermosg.msclients.application.ports.output.CuentaOutputPort;
import dev.guillermosg.msclients.application.ports.output.MovimientoOutputPort;
import dev.guillermosg.msclients.domain.exception.AccountNotExistException;
import dev.guillermosg.msclients.domain.exception.UnavailableBalanceException;
import dev.guillermosg.msclients.domain.model.Cuenta;
import dev.guillermosg.msclients.domain.model.MovimientoRequest;
import dev.guillermosg.msclients.domain.model.SuccessResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class MovimientoService implements MovimientoUseCase {

    private final MovimientoOutputPort movimientoOutputPort;
    private final CuentaOutputPort cuentaOutputPort;

    /**
     * @param movimiento
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse createMovement(MovimientoRequest movimiento) {
        Cuenta cuenta = validarCuenta(movimiento.getCuentaId());

        if(cuenta.getNumeroCuenta() == null){
            throw new AccountNotExistException();
        }

        if (movimiento.getValor().compareTo(BigDecimal.ZERO) < 0) {
            movimiento.setTipoMovimiento("RETIRO");
            System.out.println(cuenta.getSaldoActual());
            if(cuenta.getSaldoActual().compareTo(movimiento.getValor().abs()) < 0){
                throw new UnavailableBalanceException();
            }
        } else if (movimiento.getValor().compareTo(BigDecimal.ZERO) > 0) {
            movimiento.setTipoMovimiento("DEPOSITO");
        }

        movimientoOutputPort.publishSave(movimiento);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setCode("000");
        successResponse.setMessage("Operación exitosa.");

        return successResponse;
    }

    public Cuenta validarCuenta(Integer cuentaId){
        CompletableFuture<Cuenta> cuenta = cuentaOutputPort.validar(cuentaId);
        CompletableFuture<Cuenta> cuentaFuture = cuenta.thenApplyAsync(cuentaResponse -> cuentaResponse);
        return cuentaFuture.join();
    }

}
