package dev.guillermosg.msmovements.domain.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msmovements.application.ports.input.ReporteUseCase;
import dev.guillermosg.msmovements.domain.model.GenerarEstadoCuentaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReporteConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReporteUseCase reporteUseCase;
    private Logger log = LoggerFactory.getLogger(ReporteConsumer.class);

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-generar-reporte")
    public void consumeValidarMovimiento(String reporte) {
        try {
            log.info("****************************************************************");
            log.info("****************************************************************");
            log.info("Consumer Receives in Microservice Clients - Generar reporte");


            reporteUseCase.generarEstadoCuenta(objectMapper.readValue(reporte, GenerarEstadoCuentaRequest.class));

            log.info("****************************************************************");
            log.info("****************************************************************");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
