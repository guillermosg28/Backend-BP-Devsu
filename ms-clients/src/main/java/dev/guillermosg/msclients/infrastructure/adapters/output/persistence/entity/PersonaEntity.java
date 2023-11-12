package dev.guillermosg.msclients.infrastructure.adapters.output.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type Persona entity.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonaEntity implements Serializable {

    private final static long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personaId;

    private String nombre;

    private String genero;

    private int edad;

    private String identificacion;

    private String direccion;

    private String telefono;

}
