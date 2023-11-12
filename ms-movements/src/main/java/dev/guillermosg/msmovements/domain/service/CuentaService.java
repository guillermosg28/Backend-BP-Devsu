package dev.guillermosg.msmovements.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msmovements.application.ports.input.CuentaUseCase;
import dev.guillermosg.msmovements.application.ports.output.ClienteOutputPort;
import dev.guillermosg.msmovements.application.ports.output.CuentaOutputPort;
import dev.guillermosg.msmovements.domain.model.*;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CuentaService implements CuentaUseCase {

    private final CuentaOutputPort cuentaOutputPort;
    private final ClienteOutputPort clienteOutputPort;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * @param cuenta
     */
    @Override
    public void createAccount(CuentaRequest cuenta) {

        cuentaOutputPort.save(cuenta);
    }

    /**
     * @param cuenta
     */
    @Override
    public void validarCuenta(Integer cuenta) {
        Cuenta cuentaModel = new Cuenta();
        Optional<CuentaEntity> cuentaEntity = cuentaOutputPort.findById(cuenta);

        if(cuentaEntity.isPresent()){
            cuentaModel.setId(cuentaEntity.get().getId());
            cuentaModel.setNumeroCuenta(cuentaEntity.get().getNumeroCuenta());
            cuentaModel.setTipoCuenta(cuentaEntity.get().getTipoCuenta());
            cuentaModel.setSaldoInicial(cuentaEntity.get().getSaldoInicial());
            cuentaModel.setSaldoActual(cuentaEntity.get().getSaldoActual());
            cuentaModel.setEstado(cuentaEntity.get().getEstado());
        }else{
            cuentaModel.setId(Long.valueOf(cuenta));
        }

        clienteOutputPort.publishCuentaValidada(cuentaModel);

    }

    /**
     * @return AccountsResponse
     */
    @Override
    public void getAccounts() {
        AccountsResponse accountsResponse = new AccountsResponse();
        accountsResponse.setAccounts(cuentaOutputPort.findAll());

        clienteOutputPort.publishGetCuentas(accountsResponse);
    }

    /**
     * @param request
     */
    @Override
    public void updateStatusAccount(UpdateStatusAccountRequest request) {

        var cuenta = cuentaOutputPort.findById(request.getCuentaId()).orElseThrow();

        cuenta.setEstado(request.getEstado());

        cuentaOutputPort.updateStatusAccount(request);

        SuccessResponse response = new SuccessResponse();
        response.setCode("000");
        response.setMessage("Operación exitosa.");

        clienteOutputPort.publishUpdateStatusAccount(response);

    }
}
