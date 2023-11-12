package dev.guillermosg.msclients.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * MovimientosResponse model
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovimientosResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fecha;
    private String tipoMovimiento;
    private BigDecimal valor;
    private String descripcion;
}
