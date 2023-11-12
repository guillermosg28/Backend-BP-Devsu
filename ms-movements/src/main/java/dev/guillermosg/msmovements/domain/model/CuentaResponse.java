package dev.guillermosg.msmovements.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * CuentaResponse model
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer clienteId;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private BigDecimal saldoActual;
    private Boolean estado;
    private List<MovimientosResponse> movimientos = null;

}
