package dev.guillermosg.msmovements.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * EstadoCuentaResponse model
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoCuentaResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cliente;
    private String clienteId;
    private List<CuentaResponse> cuentas;

}
