package dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Movimiento entity.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "movimientos")
public class MovimientoEntity implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fecha;

    private String tipoMovimiento;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", nullable = false)
    private CuentaEntity cuenta;

}
