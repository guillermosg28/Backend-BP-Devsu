package dev.guillermosg.msmovements.application.ports.output;

import dev.guillermosg.msmovements.domain.model.*;

/**
 * The interface Cuentas output port.
 */
public interface ClienteOutputPort {

    void publishCuentaValidada(Cuenta cuenta);

    void publishReporteGenerado(EstadoCuentaResponse reporte);

    void publishGetCuentas(AccountsResponse cuentas);

    void publishUpdateStatusAccount(SuccessResponse response);

}
