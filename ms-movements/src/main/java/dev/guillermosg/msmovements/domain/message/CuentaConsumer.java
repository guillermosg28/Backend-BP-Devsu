package dev.guillermosg.msmovements.domain.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msmovements.application.ports.input.CuentaUseCase;
import dev.guillermosg.msmovements.domain.model.CuentaRequest;
import dev.guillermosg.msmovements.domain.model.UpdateStatusAccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CuentaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CuentaUseCase cuentaUseCase;

    private Logger log = LoggerFactory.getLogger(CuentaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "crearcuenta")
    public void consumeCuenta(String cuenta) {
        try {
            log.info("****************************************************************");
            log.info("****************************************************************");
            log.info("Consumer Receives in Microservice Clients");

            CuentaRequest cuentaRequest = objectMapper.readValue(cuenta, CuentaRequest.class);
            cuentaUseCase.createAccount(cuentaRequest);

            log.info("****************************************************************");
            log.info("****************************************************************");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-validar-cuenta")
    public void consumeValidarMovimiento(String cuentaId) {
        try {
            log.info("****************************************************************");
            log.info("****************************************************************");
            log.info("Consumer Receives in Microservice Clients - Validar cuenta");

            cuentaUseCase.validarCuenta(Integer.valueOf(cuentaId));

            log.info("****************************************************************");
            log.info("****************************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-ver-cuentas")
    public void consumeGetCuentas(String cuentas) {
        try {
            log.info("****************************************************************");
            log.info("****************************************************************");
            log.info("Consumer Receives in Microservice Clients - Ver cuentas");

            cuentaUseCase.getAccounts();

            log.info("****************************************************************");
            log.info("****************************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-update-estado-cuenta")
    public void consumeUpdateeSTADCuenta(String request) {
        try {
            log.info("****************************************************************");
            log.info("****************************************************************");
            log.info("Consumer Receives in Microservice Clients - Actualizar estado de cuenta");

            cuentaUseCase.updateStatusAccount(objectMapper.readValue(request, UpdateStatusAccountRequest.class));

            log.info("****************************************************************");
            log.info("****************************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
