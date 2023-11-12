package dev.guillermosg.msclients.application.ports.input;


import dev.guillermosg.msclients.domain.model.AccountsResponse;
import dev.guillermosg.msclients.domain.model.CuentaRequest;
import dev.guillermosg.msclients.domain.model.SuccessResponse;
import dev.guillermosg.msclients.domain.model.UpdateStatusAccountRequest;

/**
 * The interface Cuentas use case.
 */
public interface CuentaUseCase {

    SuccessResponse createAccount(CuentaRequest cuenta);

    AccountsResponse getAccounts();

    SuccessResponse updateStatusAccount(Integer cuentaId, UpdateStatusAccountRequest request);
}
