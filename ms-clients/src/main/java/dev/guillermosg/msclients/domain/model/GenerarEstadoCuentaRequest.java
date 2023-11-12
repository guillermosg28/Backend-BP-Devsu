package dev.guillermosg.msclients.domain.model;

import lombok.*;

import java.io.Serializable;

/**
 * GenerarEstadoCuentaRequest model
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerarEstadoCuentaRequest implements Serializable {

        private static final long serialVersionUID = 1L;

        private String fechaInicio;
        private String fechaFin;
        private Integer clienteId;
        private String cliente;
}
