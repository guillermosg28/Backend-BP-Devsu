package dev.guillermosg.msclients.infrastructure.adapters.output.persistence;

import dev.guillermosg.msclients.application.ports.output.ClienteOutputPort;
import dev.guillermosg.msclients.domain.model.Cliente;
import dev.guillermosg.msclients.infrastructure.adapters.output.persistence.mapper.ClientePersistenceMapper;
import dev.guillermosg.msclients.infrastructure.adapters.output.persistence.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientePersistenceAdapter implements ClienteOutputPort {

    private final ClienteRepository clienteRepository;
    private final ClientePersistenceMapper clientePersistenceMapper;

    /**
     * @param cliente
     */
    @Override
    public void save(Cliente cliente) {
        clienteRepository.save(clientePersistenceMapper.toClienteEntity(cliente));
    }

    /**
     * @param clienteId
     * @return Cliente
     */
    @Override
    public Optional<Cliente> findByClienteId(Integer clienteId) {
        return Optional.ofNullable(clientePersistenceMapper.toCliente(clienteRepository.findByClienteId(clienteId.longValue())));
    }

    /**
     * @return List<ClienteRequest>
     */
    @Override
    public List<Cliente> findAll() {
        return clienteRepository
                .findAll().stream().map(clientePersistenceMapper::toCliente)
                .collect(Collectors.toList());
    }

    /**
     * @param cliente
     * @return Cliente
     */
    @Override
    public Cliente update(Cliente cliente) {
        return clientePersistenceMapper.toCliente(clienteRepository.save(clientePersistenceMapper.toClienteEntity(cliente)));
    }

    /**
     * @param cliente
     */
    @Override
    public void delete(Cliente cliente) {
        clienteRepository.delete(clientePersistenceMapper.toClienteEntity(cliente));
    }
}
