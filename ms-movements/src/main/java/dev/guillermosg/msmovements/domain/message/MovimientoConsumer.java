package dev.guillermosg.msmovements.domain.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msmovements.application.ports.input.MovimientoUseCase;
import dev.guillermosg.msmovements.domain.model.MovimientoRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MovimientoConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovimientoUseCase movimientoUseCase;


    private Logger log = LoggerFactory.getLogger(MovimientoConsumer.class);

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-crear-movimiento")
    public void consumeValidarMovimiento(String movimiento) {
        try {
            log.info("****************************************************************");
            log.info("****************************************************************");
            log.info("Consumer Receives in Microservice Clients - Crear movimiento");

            movimientoUseCase.save(objectMapper.readValue(movimiento, MovimientoRequest.class));

            log.info("****************************************************************");
            log.info("****************************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
