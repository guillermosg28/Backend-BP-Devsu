package dev.guillermosg.msclients.infrastructure.adapters.output.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msclients.application.ports.output.ReporteOutputPort;
import dev.guillermosg.msclients.domain.model.EstadoCuentaResponse;
import dev.guillermosg.msclients.domain.model.GenerarEstadoCuentaRequest;
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
public class ReporteIntegrationAdapter implements ReporteOutputPort {

    @Value("${spring.kafka.template.default-topic}")
    String topicName;

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private Logger log = LoggerFactory.getLogger(CuentaIntegrationAdapter.class);

    private final Map<String, CompletableFuture<EstadoCuentaResponse>> futures = new ConcurrentHashMap<>();

    /**
     * @param reporte
     * @return EstadoCuentaResponse
     */
    @Override
    public CompletableFuture<EstadoCuentaResponse> generarEstadoCuenta(GenerarEstadoCuentaRequest reporte) {
        try {
            CompletableFuture<EstadoCuentaResponse> future = new CompletableFuture<>();
            futures.put(reporte.getClienteId().toString(), future);
            kafkaTemplate.send(topicName + "-generar-reporte", objectMapper.writeValueAsString(reporte));
            return future;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}" + "-reporte-generado")
    public void respuestaValidar(@Payload String reporte) {
        try {
            EstadoCuentaResponse cuentaObj = objectMapper.readValue(reporte, EstadoCuentaResponse.class);
            CompletableFuture<EstadoCuentaResponse> future = futures.remove(cuentaObj.getClienteId());
            if (future != null) {
                log.info("Completando future del reporte para clienteId: " + cuentaObj.getClienteId());
                future.complete(cuentaObj);
            } else {
                log.warn("No se encontró un future para clienteId: " + cuentaObj.getClienteId());
            }
        } catch (JsonProcessingException e) {
            log.error("Error al procesar JSON para cuenta: " + reporte, e);
        }
    }

}
