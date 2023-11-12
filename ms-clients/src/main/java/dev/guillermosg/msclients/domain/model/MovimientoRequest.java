package dev.guillermosg.msclients.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * MovimientoRequest model
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cuentaId;
    private String fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
}
