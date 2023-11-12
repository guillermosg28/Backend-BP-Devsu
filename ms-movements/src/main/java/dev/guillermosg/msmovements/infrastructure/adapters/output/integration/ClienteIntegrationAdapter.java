package dev.guillermosg.msmovements.infrastructure.adapters.output.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msmovements.application.ports.output.ClienteOutputPort;
import dev.guillermosg.msmovements.domain.model.AccountsResponse;
import dev.guillermosg.msmovements.domain.model.Cuenta;
import dev.guillermosg.msmovements.domain.model.EstadoCuentaResponse;
import dev.guillermosg.msmovements.domain.model.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClienteIntegrationAdapter implements ClienteOutputPort {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * @param cuenta
     */
    @Override
    public void publishCuentaValidada(Cuenta cuenta) {
        try {
            String cuentaJson = objectMapper.writeValueAsString(cuenta);
            kafkaTemplate.send(topicName + "-cuenta-validada", cuentaJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param reporte
     */
    @Override
    public void publishReporteGenerado(EstadoCuentaResponse reporte) {
        try {
            String cuentaJson = objectMapper.writeValueAsString(reporte);
            kafkaTemplate.send(topicName + "-reporte-generado", cuentaJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param cuentas
     */
    @Override
    public void publishGetCuentas(AccountsResponse cuentas) {
        try {
            String cuentaJson = objectMapper.writeValueAsString(cuentas);
            kafkaTemplate.send(topicName + "-recibir-cuentas", cuentaJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param response
     */
    @Override
    public void publishUpdateStatusAccount(SuccessResponse response) {
        try {
            String cuentaJson = objectMapper.writeValueAsString(response);
            kafkaTemplate.send(topicName + "-recibir-actualizacion-cuenta", cuentaJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
