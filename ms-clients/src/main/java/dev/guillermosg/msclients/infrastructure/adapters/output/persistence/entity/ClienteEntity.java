package dev.guillermosg.msclients.infrastructure.adapters.output.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The type Cliente entity.
 */

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clientes")
public class ClienteEntity extends PersonaEntity{


    @Column(unique = true)
    private Long clienteId;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "estado")
    private boolean estado;

}
