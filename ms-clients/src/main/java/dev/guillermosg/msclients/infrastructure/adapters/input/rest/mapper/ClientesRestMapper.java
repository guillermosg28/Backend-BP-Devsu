package dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper;


import dev.guillermosg.msclients.domain.model.Cliente;
import dev.guillermosg.msclients.domain.model.ClientesResponse;
import dev.guillermosg.msclients.domain.model.UpdateStatusRequest;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.ClientDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.ClientsResponseDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.UpdateStatusRequestDto;
import org.mapstruct.Mapper;

/**
 * The interface SkinsRestMapper
 */
@Mapper
public interface ClientesRestMapper {


    ClientsResponseDto clientsResponseToDto(ClientesResponse clientesResponse);

    ClientDto clientToDto(Cliente cliente);

    Cliente clienteToDomain(ClientDto clientDto);

    UpdateStatusRequest updateStatusRequestToDomain(UpdateStatusRequestDto dto);
}
