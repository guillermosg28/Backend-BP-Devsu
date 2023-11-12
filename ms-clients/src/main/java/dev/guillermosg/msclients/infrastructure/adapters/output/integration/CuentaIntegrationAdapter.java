package dev.guillermosg.msclients.infrastructure.adapters.output.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msclients.application.ports.output.CuentaOutputPort;
import dev.guillermosg.msclients.domain.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CuentaIntegrationAdapter implements CuentaOutputPort {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private final Map<String, CompletableFuture<Cuenta>> futures = new ConcurrentHashMap<>();

    private final Map<String, CompletableFuture<AccountsResponse>> futureGetCuentas = new ConcurrentHashMap<>();

    private final Map<String, CompletableFuture<SuccessResponse>> futureStatusCuenta = new ConcurrentHashMap<>();

    private Logger log = LoggerFactory.getLogger(CuentaIntegrationAdapter.class);

    /**
     * @param cuentaId
     * @return CompletableFuture<Cuenta>
     */
    public CompletableFuture<Cuenta> validar(Integer cuentaId) {
        CompletableFuture<Cuenta> future = new CompletableFuture<>();
        futures.put(cuentaId.toString(), future);

        kafkaTemplate.send(topicName + "-validar-cuenta", cuentaId.toString());

        return future;
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-cuenta-validada")
    public void respuestaValidar(@Payload String cuenta) {
        try {
            Cuenta cuentaObj = objectMapper.readValue(cuenta, Cuenta.class);
            CompletableFuture<Cuenta> future = futures.remove(cuentaObj.getId().toString());
            if (future != null) {
                log.info("Completando future para cuentaId: " + cuentaObj.getId());
                future.complete(cuentaObj);
            } else {
                log.warn("No se encontró un future para cuentaId: " + cuentaObj.getId());
            }
        } catch (JsonProcessingException e) {
            log.error("Error al procesar JSON para cuenta: " + cuenta, e);
        }
    }

    /**
     * @param cuenta
     */
    @Override
    public void publish(CuentaRequest cuenta) {
        try {
            String cuentaJson = objectMapper.writeValueAsString(cuenta);

            kafkaTemplate.send(topicName + "crearcuenta", cuentaJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * @return CompletableFuture<AccountsResponse>
     */
    @Override
    public CompletableFuture<AccountsResponse> getAccounts() {
        CompletableFuture<AccountsResponse> future = new CompletableFuture<>();
        futureGetCuentas.put("get-cuentas", future);
        kafkaTemplate.send(topicName + "-ver-cuentas", "get-cuentas");
        return future;
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-recibir-cuentas")
    public void respuestaGetCuentas(@Payload String cuentas) {
        try {
            AccountsResponse cuentaObj = objectMapper.readValue(cuentas, AccountsResponse.class);
            CompletableFuture<AccountsResponse> future = futureGetCuentas.remove("get-cuentas");
            if (future != null) {
                log.info("Completando future para : " + "get-cuentas");
                future.complete(cuentaObj);
            } else {
                log.warn("No se encontró un future para : " + "get-cuentas");
            }
        } catch (JsonProcessingException e) {
            log.error("Error al procesar JSON para : " + "get-cuentas", e);
        }
    }

    /**
     * @param request
     * @return CompletableFuture<SuccessResponse>
     */
    @Override
    public CompletableFuture<SuccessResponse> updateStatusAccount(UpdateStatusAccountRequest request) {
        try {
            CompletableFuture<SuccessResponse> future = new CompletableFuture<>();
            futureStatusCuenta.put("actualizar-cuenta", future);

            String requestJson = objectMapper.writeValueAsString(request);

            kafkaTemplate.send(topicName + "-update-estado-cuenta", requestJson);

            return future;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-recibir-actualizacion-cuenta")
    public void respuestaActualizarCuenta(@Payload String cuentas) {
        try {
            SuccessResponse cuentaObj = objectMapper.readValue(cuentas, SuccessResponse.class);
            CompletableFuture<SuccessResponse> future = futureStatusCuenta.remove("actualizar-cuenta");
            if (future != null) {
                log.info("Completando future para : " + "actualizar-cuenta");
                future.complete(cuentaObj);
            } else {
                log.warn("No se encontró un future para : " + "actualizar-cuenta");
            }
        } catch (JsonProcessingException e) {
            log.error("Error al procesar JSON para : " + "actualizar-cuenta", e);
        }
    }

}
