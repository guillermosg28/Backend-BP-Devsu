package dev.guillermosg.msclients.domain.model;

import lombok.*;
import java.io.Serializable;

/**
 * The model Persona.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;

}
