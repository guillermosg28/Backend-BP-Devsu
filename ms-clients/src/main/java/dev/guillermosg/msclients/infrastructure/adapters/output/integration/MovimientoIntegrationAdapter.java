package dev.guillermosg.msclients.infrastructure.adapters.output.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msclients.application.ports.output.MovimientoOutputPort;
import dev.guillermosg.msclients.domain.model.MovimientoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * The Movimiento integration adapter.
 */

@Component
public class MovimientoIntegrationAdapter implements MovimientoOutputPort {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * @param movimiento
     */
    @Override
    public void publishSave(MovimientoRequest movimiento) {
        try {
            String movimientoJson = objectMapper.writeValueAsString(movimiento);
            kafkaTemplate.send(topicName + "-crear-movimiento", movimientoJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
