package dev.guillermosg.msclients.domain.model;

import lombok.*;


/**
 * The model Cliente.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    private Long clienteId;
    private String contrasena;
    private boolean estado;

    private Long personaId;
    private String nombre;
    private String genero;
    private int edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
