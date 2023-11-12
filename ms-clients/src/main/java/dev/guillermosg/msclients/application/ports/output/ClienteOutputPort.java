package dev.guillermosg.msclients.application.ports.output;

import dev.guillermosg.msclients.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * The interface Clientes output port.
 */
public interface ClienteOutputPort {

    void save(Cliente cliente);

    Optional<Cliente> findByClienteId(Integer clienteId);

    List<Cliente> findAll();

    Cliente update(Cliente cliente);

    void delete(Cliente cliente);

}
