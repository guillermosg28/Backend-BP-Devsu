package dev.guillermosg.msclients.domain.service;

import dev.guillermosg.msclients.application.ports.input.CuentaUseCase;
import dev.guillermosg.msclients.application.ports.output.ClienteOutputPort;
import dev.guillermosg.msclients.application.ports.output.CuentaOutputPort;
import dev.guillermosg.msclients.domain.exception.AccountNotExistException;
import dev.guillermosg.msclients.domain.exception.ClientNotExistException;
import dev.guillermosg.msclients.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
@Slf4j
public class CuentaService implements CuentaUseCase {

    private final ClienteOutputPort clienteOutputPort;
    private final CuentaOutputPort cuentaOutputPort;

    /**
     * @param cuenta
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse createAccount(CuentaRequest cuenta) {
        SuccessResponse successResponse = new SuccessResponse();
        var cliente = clienteOutputPort.findByClienteId(cuenta.getClienteId())
                .orElseThrow(ClientNotExistException::new);

        cuentaOutputPort.publish(cuenta);

        successResponse.setCode("000");
        successResponse.setMessage("Operación exitosa.");

        return successResponse;
    }

    /**
     * @return AccountsResponse
     */
    @Override
    public AccountsResponse getAccounts() {
        return cuentaOutputPort.getAccounts().join();
    }

    /**
     * @param cuentaId
     * @param request
     * @return
     */
    @Override
    public SuccessResponse updateStatusAccount(Integer cuentaId, UpdateStatusAccountRequest request) {

        Cuenta cuenta = validarCuenta(cuentaId);

        if(cuenta.getNumeroCuenta() == null){
            throw new AccountNotExistException();
        }

        request.setCuentaId(cuentaId);
        return cuentaOutputPort.updateStatusAccount(request).join();

    }

    private Cuenta validarCuenta(Integer cuentaId){
        CompletableFuture<Cuenta> cuenta = cuentaOutputPort.validar(cuentaId);
        CompletableFuture<Cuenta> cuentaFuture = cuenta.thenApplyAsync(cuentaResponse -> cuentaResponse);
        return cuentaFuture.join();
    }

}
