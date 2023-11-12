package dev.guillermosg.msclients.domain.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * ClientesResponse model
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientesResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Cliente> clients;
}
