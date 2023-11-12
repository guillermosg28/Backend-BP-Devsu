package dev.guillermosg.msclients.domain.service;

import dev.guillermosg.msclients.application.ports.output.MovimientoOutputPort;
import dev.guillermosg.msclients.domain.exception.AccountNotExistException;
import dev.guillermosg.msclients.domain.model.Cuenta;
import dev.guillermosg.msclients.domain.model.MovimientoRequest;
import dev.guillermosg.msclients.domain.model.SuccessResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class MovimientoServiceIntegrationTest {

    @Mock
    private MovimientoOutputPort movimientoOutputPort;

    @Spy
    @InjectMocks
    private MovimientoService movimientoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateMovement_Deposito() {
        MovimientoRequest movimientoRequest = new MovimientoRequest();
        movimientoRequest.setCuentaId(1);
        movimientoRequest.setValor(BigDecimal.TEN);

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldoActual(BigDecimal.valueOf(100));
        doReturn(cuenta).when(movimientoService).validarCuenta(anyInt());

        doNothing().when(movimientoOutputPort).publishSave(any(MovimientoRequest.class));

        SuccessResponse successResponse = movimientoService.createMovement(movimientoRequest);

        verify(movimientoOutputPort, times(1)).publishSave(movimientoRequest);

        assertEquals("000", successResponse.getCode());
        assertEquals("Operación exitosa.", successResponse.getMessage());

    }

    @Test
    public void testCreateMovement_Retiro_SaldoSuficiente() {
        MovimientoRequest movimientoRequest = new MovimientoRequest();
        movimientoRequest.setCuentaId(1);
        movimientoRequest.setValor(BigDecimal.valueOf(50));

        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta("123456");
        cuenta.setSaldoActual(BigDecimal.valueOf(100));
        doReturn(cuenta).when(movimientoService).validarCuenta(anyInt());

        doNothing().when(movimientoOutputPort).publishSave(any(MovimientoRequest.class));

        SuccessResponse successResponse = movimientoService.createMovement(movimientoRequest);

        verify(movimientoOutputPort, times(1)).publishSave(movimientoRequest);

        assertEquals("000", successResponse.getCode());
        assertEquals("Operación exitosa.", successResponse.getMessage());
    }

    @Test
    public void testCreateMovement_CuentaNoExiste() {
        MovimientoRequest movimientoRequest = new MovimientoRequest();
        movimientoRequest.setCuentaId(999);

        doReturn(new Cuenta()).when(movimientoService).validarCuenta(anyInt());

        try {
            movimientoService.createMovement(movimientoRequest);
            fail("Se esperaba AccountNotExistException, pero no fue lanzada.");
        } catch (AccountNotExistException e) {
        }

        verify(movimientoOutputPort, never()).publishSave(any(MovimientoRequest.class));
    }
}
