package dev.guillermosg.msclients.domain.model;

import lombok.*;

import java.io.Serializable;

/**
 * UpdateStatusRequest model
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean estado;
}
