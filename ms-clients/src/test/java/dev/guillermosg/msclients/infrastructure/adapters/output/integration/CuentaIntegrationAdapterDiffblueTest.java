package dev.guillermosg.msclients.infrastructure.adapters.output.integration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.guillermosg.msclients.domain.model.AccountsResponse;
import dev.guillermosg.msclients.domain.model.Cuenta;
import dev.guillermosg.msclients.domain.model.CuentaRequest;
import dev.guillermosg.msclients.domain.model.SuccessResponse;
import dev.guillermosg.msclients.domain.model.UpdateStatusAccountRequest;

import java.util.ArrayList;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CuentaIntegrationAdapter.class})
@ExtendWith(SpringExtension.class)
@PropertySource("classpath:application.properties")
@EnableConfigurationProperties
class CuentaIntegrationAdapterDiffblueTest {
  @Autowired
  private CuentaIntegrationAdapter cuentaIntegrationAdapter;

  @MockBean
  private KafkaTemplate<Integer, String> kafkaTemplate;

  @MockBean
  private ObjectMapper objectMapper;

  /**
   * Method under test: {@link CuentaIntegrationAdapter#validar(Integer)}
   */
  @Test
  void testValidar() {
    ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>("Topic", "42");

    when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new AsyncResult<>(
            new SendResult<>(producerRecord, new RecordMetadata(new TopicPartition("Topic", 1), 1L, 1, 10L, 3, 3))));
    cuentaIntegrationAdapter.validar(1);
    verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#validar(Integer)}
   */
  @Test
  void testValidar2() {
    when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenThrow(new RuntimeException("foo"));
    assertThrows(RuntimeException.class, () -> cuentaIntegrationAdapter.validar(1));
    verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#respuestaValidar(String)}
   */
  @Test
  void testRespuestaValidar() throws JsonProcessingException {
    Cuenta cuenta = new Cuenta();
    cuenta.setId(1);
    when(objectMapper.readValue(Mockito.<String>any(), Mockito.<Class<Cuenta>>any())).thenReturn(cuenta);
    cuentaIntegrationAdapter.respuestaValidar("Cuenta");
    verify(objectMapper).readValue(Mockito.<String>any(), Mockito.<Class<Cuenta>>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#respuestaValidar(String)}
   */
  @Test
  void testRespuestaValidar2() throws JsonProcessingException {
    when(objectMapper.readValue(Mockito.<String>any(), Mockito.<Class<Cuenta>>any()))
            .thenThrow(new RuntimeException("foo"));
    assertThrows(RuntimeException.class, () -> cuentaIntegrationAdapter.respuestaValidar("Cuenta"));
    verify(objectMapper).readValue(Mockito.<String>any(), Mockito.<Class<Cuenta>>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#publish(CuentaRequest)}
   */
  @Test
  void testPublish() throws JsonProcessingException {
    ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>("Topic", "42");

    when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new AsyncResult<>(
            new SendResult<>(producerRecord, new RecordMetadata(new TopicPartition("Topic", 1), 1L, 1, 10L, 3, 3))));
    when(objectMapper.writeValueAsString(Mockito.<Object>any())).thenReturn("42");
    cuentaIntegrationAdapter.publish(new CuentaRequest());
    verify(objectMapper).writeValueAsString(Mockito.<Object>any());
    verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#publish(CuentaRequest)}
   */
  @Test
  void testPublish2() throws JsonProcessingException {
    when(objectMapper.writeValueAsString(Mockito.<Object>any())).thenThrow(new RuntimeException("foo"));
    assertThrows(RuntimeException.class, () -> cuentaIntegrationAdapter.publish(new CuentaRequest()));
    verify(objectMapper).writeValueAsString(Mockito.<Object>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#getAccounts()}
   */
  @Test
  void testGetAccounts() {
    ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>("Topic", "42");

    when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new AsyncResult<>(
            new SendResult<>(producerRecord, new RecordMetadata(new TopicPartition("Topic", 1), 1L, 1, 10L, 3, 3))));
    cuentaIntegrationAdapter.getAccounts();
    verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#getAccounts()}
   */
  @Test
  void testGetAccounts2() {
    when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any()))
            .thenThrow(new RuntimeException("get-cuentas"));
    assertThrows(RuntimeException.class, () -> cuentaIntegrationAdapter.getAccounts());
    verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#respuestaGetCuentas(String)}
   */
  @Test
  void testRespuestaGetCuentas() throws JsonProcessingException {
    AccountsResponse.AccountsResponseBuilder builderResult = AccountsResponse.builder();
    AccountsResponse buildResult = builderResult.accounts(new ArrayList<>()).build();
    when(objectMapper.readValue(Mockito.<String>any(), Mockito.<Class<AccountsResponse>>any())).thenReturn(buildResult);
    cuentaIntegrationAdapter.respuestaGetCuentas("Cuentas");
    verify(objectMapper).readValue(Mockito.<String>any(), Mockito.<Class<AccountsResponse>>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#respuestaGetCuentas(String)}
   */
  @Test
  void testRespuestaGetCuentas2() throws JsonProcessingException {
    when(objectMapper.readValue(Mockito.<String>any(), Mockito.<Class<AccountsResponse>>any()))
            .thenThrow(new RuntimeException("get-cuentas"));
    assertThrows(RuntimeException.class, () -> cuentaIntegrationAdapter.respuestaGetCuentas("Cuentas"));
    verify(objectMapper).readValue(Mockito.<String>any(), Mockito.<Class<AccountsResponse>>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#updateStatusAccount(UpdateStatusAccountRequest)}
   */
  @Test
  void testUpdateStatusAccount() throws JsonProcessingException {
    ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>("Topic", "42");

    when(kafkaTemplate.send(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new AsyncResult<>(
            new SendResult<>(producerRecord, new RecordMetadata(new TopicPartition("Topic", 1), 1L, 1, 10L, 3, 3))));
    when(objectMapper.writeValueAsString(Mockito.<Object>any())).thenReturn("42");
    cuentaIntegrationAdapter.updateStatusAccount(new UpdateStatusAccountRequest());
    verify(objectMapper).writeValueAsString(Mockito.<Object>any());
    verify(kafkaTemplate).send(Mockito.<String>any(), Mockito.<String>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#updateStatusAccount(UpdateStatusAccountRequest)}
   */
  @Test
  void testUpdateStatusAccount2() throws JsonProcessingException {
    when(objectMapper.writeValueAsString(Mockito.<Object>any())).thenThrow(new RuntimeException("actualizar-cuenta"));
    assertThrows(RuntimeException.class,
            () -> cuentaIntegrationAdapter.updateStatusAccount(new UpdateStatusAccountRequest()));
    verify(objectMapper).writeValueAsString(Mockito.<Object>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#respuestaActualizarCuenta(String)}
   */
  @Test
  void testRespuestaActualizarCuenta() throws JsonProcessingException {
    SuccessResponse buildResult = SuccessResponse.builder().code("Code").message("Not all who wander are lost").build();
    when(objectMapper.readValue(Mockito.<String>any(), Mockito.<Class<SuccessResponse>>any())).thenReturn(buildResult);
    cuentaIntegrationAdapter.respuestaActualizarCuenta("Cuentas");
    verify(objectMapper).readValue(Mockito.<String>any(), Mockito.<Class<SuccessResponse>>any());
  }

  /**
   * Method under test: {@link CuentaIntegrationAdapter#respuestaActualizarCuenta(String)}
   */
  @Test
  void testRespuestaActualizarCuenta2() throws JsonProcessingException {
    when(objectMapper.readValue(Mockito.<String>any(), Mockito.<Class<SuccessResponse>>any()))
            .thenThrow(new RuntimeException("actualizar-cuenta"));
    assertThrows(RuntimeException.class, () -> cuentaIntegrationAdapter.respuestaActualizarCuenta("Cuentas"));
    verify(objectMapper).readValue(Mockito.<String>any(), Mockito.<Class<SuccessResponse>>any());
  }
}
