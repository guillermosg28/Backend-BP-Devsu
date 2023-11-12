package dev.guillermosg.msclients.application.ports.output;

import dev.guillermosg.msclients.domain.model.*;

import java.util.concurrent.CompletableFuture;

public interface CuentaOutputPort {

    CompletableFuture<Cuenta> validar(Integer cuentaId);

    void publish(CuentaRequest cuenta);

    CompletableFuture<AccountsResponse> getAccounts();

    CompletableFuture<SuccessResponse> updateStatusAccount(UpdateStatusAccountRequest request);
}
