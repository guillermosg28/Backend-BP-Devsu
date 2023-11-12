package dev.guillermosg.msclients.infrastructure.adapters.output.persistence.mapper;

import dev.guillermosg.msclients.domain.model.Cliente;
import dev.guillermosg.msclients.infrastructure.adapters.output.persistence.entity.ClienteEntity;
import org.mapstruct.Mapper;

/**
 * The interface Cliente persistence mapper.
 */

@Mapper
public interface ClientePersistenceMapper {


    Cliente toCliente(ClienteEntity entity);

    ClienteEntity toClienteEntity(Cliente cliente);

}
