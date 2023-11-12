package dev.guillermosg.msclients.domain.service;

import dev.guillermosg.msclients.application.ports.input.ClienteUseCase;
import dev.guillermosg.msclients.application.ports.output.ClienteOutputPort;
import dev.guillermosg.msclients.domain.exception.ClientNotExistException;
import dev.guillermosg.msclients.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class ClienteService implements ClienteUseCase {

    private final ClienteOutputPort clienteOutputPort;
    /**
     * @param cliente
     * @return SuccessResponse
     */
    @Override
    @Transactional
    public SuccessResponse createClient(Cliente cliente) {
        SuccessResponse successResponse = new SuccessResponse();
        cliente.setClienteId(cliente.getPersonaId());
        cliente.setEstado(true);
        clienteOutputPort.save(cliente);
        successResponse.setCode("000");
        successResponse.setMessage("Operación exitosa.");
        return successResponse;
    }

    /**
     * @return ClientesResponse
     */
    @Override
    public ClientesResponse getClients() {

        ClientesResponse clientesResponse = new ClientesResponse();
        clientesResponse.setClients(clienteOutputPort.findAll());
        return clientesResponse;
    }

    /**
     * @param cliente
     * @return
     */
    @Override
    public Cliente updateClient(Integer clienteId, Cliente cliente) {

        var clienteDomain = clienteOutputPort.findByClienteId(clienteId).orElseThrow(ClientNotExistException::new);

        cliente.setClienteId(clienteDomain.getClienteId());
        cliente.setPersonaId(clienteDomain.getPersonaId());

        return clienteOutputPort.update(cliente);
    }

    /**
     * @param clienteId
     * @param updateStatusRequest
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse updateStatusClient(Integer clienteId, UpdateStatusRequest updateStatusRequest) {

        SuccessResponse successResponse = new SuccessResponse();

        var clienteDomain = clienteOutputPort.findByClienteId(clienteId).orElseThrow(ClientNotExistException::new);
        clienteDomain.setEstado(updateStatusRequest.getEstado());

        clienteOutputPort.update(clienteDomain);

        successResponse.setCode("000");
        successResponse.setMessage("Operación exitosa.");
        return successResponse;
    }

    /**
     * @param clienteId
     * @return Cliente
     */
    @Override
    public Cliente getClient(Integer clienteId) {

        var cliente = clienteOutputPort.findByClienteId(clienteId).orElseThrow(ClientNotExistException::new);

        return cliente;
    }

    /**
     * @param clienteId
     * @return SuccessResponse
     */
    @Override
    public SuccessResponse deleteClient(Integer clienteId) {

        SuccessResponse successResponse = new SuccessResponse();

        var cliente = clienteOutputPort.findByClienteId(clienteId).orElseThrow(ClientNotExistException::new);

        clienteOutputPort.delete(cliente);

        successResponse.setCode("000");
        successResponse.setMessage("Operación exitosa.");

        return successResponse;
    }
}
