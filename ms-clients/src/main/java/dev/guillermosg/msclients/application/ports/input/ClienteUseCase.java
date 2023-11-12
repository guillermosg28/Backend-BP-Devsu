package dev.guillermosg.msclients.application.ports.input;

import dev.guillermosg.msclients.domain.model.*;

/**
 * The interface Cliente use case.
 */
public interface ClienteUseCase {

    SuccessResponse createClient(Cliente cliente);

    ClientesResponse getClients();

    Cliente updateClient(Integer clienteId, Cliente cliente);

    SuccessResponse updateStatusClient(Integer clienteId, UpdateStatusRequest updateStatusRequest);

    Cliente getClient(Integer clienteId);

    SuccessResponse deleteClient(Integer clienteId);
}
