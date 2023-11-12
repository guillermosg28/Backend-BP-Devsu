package dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * The type Cuenta entity.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cuentas")
public class CuentaEntity implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private BigDecimal saldoActual;

    private Boolean estado;

    private Integer clienteId;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<MovimientoEntity> movimientos;
}
