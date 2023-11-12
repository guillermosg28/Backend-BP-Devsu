package dev.guillermosg.msclients.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Cuenta model
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer clienteId;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
}
