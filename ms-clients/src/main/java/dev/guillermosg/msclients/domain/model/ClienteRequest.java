package dev.guillermosg.msclients.domain.model;


import lombok.*;

import java.io.Serializable;

/**
 * Cliente model
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Persona persona;
    private String contrasena;
}
