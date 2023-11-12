package dev.guillermosg.msclients.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The model Cuenta.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal saldoActual;
    private Boolean estado;
    private Integer clienteId;

}
