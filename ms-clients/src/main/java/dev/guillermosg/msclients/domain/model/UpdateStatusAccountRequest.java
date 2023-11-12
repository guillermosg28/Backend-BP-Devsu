package dev.guillermosg.msclients.domain.model;

import lombok.*;

import java.io.Serializable;

/**
 * UpdateStatusAccountRequest model
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusAccountRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean estado;
    private Integer cuentaId;
}
