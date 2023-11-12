package dev.guillermosg.msmovements.application.ports.input;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.guillermosg.msmovements.domain.model.CuentaRequest;
import dev.guillermosg.msmovements.domain.model.UpdateStatusAccountRequest;

/**
 * The interface Cuenta use case.
 */
public interface CuentaUseCase {

    void createAccount(CuentaRequest cuenta);

    void validarCuenta(Integer cuenta) throws JsonProcessingException;

    void getAccounts();

    void updateStatusAccount(UpdateStatusAccountRequest request);
}
