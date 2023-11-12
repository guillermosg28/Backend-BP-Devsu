package dev.guillermosg.msclients.infrastructure.adapters.input.rest;

import dev.guillermosg.msclients.application.ports.input.ClienteUseCase;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.ClientDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.ClientsResponseDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.SuccessResponseDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.UpdateStatusRequestDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper.ClientesRestMapper;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper.SuccessResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Clientes rest adapter.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class ClientesRestAdapter implements ClientesApi{

    private final ClientesRestMapper clientesRestMapper;
    private final ClienteUseCase clienteUseCase;
    private final SuccessResponseMapper successResponseMapper;


    /**
     * @param clientDto Client to create (required)
     * @return SuccessResponseDto
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _createClient(ClientDto clientDto) {
        return ResponseEntity.ok().body(successResponseMapper
                .successResponseToDto(clienteUseCase.createClient(clientesRestMapper.clienteToDomain(clientDto))));
    }

    /**
     * @param clienteId Client id (required)
     * @return SuccessResponseDto
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _deleteClient(Integer clienteId) {

        return ResponseEntity.ok().body(successResponseMapper
                .successResponseToDto(clienteUseCase.deleteClient(clienteId)));
    }

    /**
     * @param clienteId Client id (required)
     * @return ClientDto
     */
    @Override
    public ResponseEntity<ClientDto> _getClient(Integer clienteId) {

        return ResponseEntity.ok().body(clientesRestMapper
                .clientToDto(clienteUseCase.getClient(clienteId)));

    }

    /**
     * @return ClientsResponseDto
     */
    @Override
    public ResponseEntity<ClientsResponseDto> _listClients() {
        return ResponseEntity.ok().body(clientesRestMapper
                .clientsResponseToDto(clienteUseCase.getClients()));
    }

    /**
     * @param clienteId              Client id (required)
     * @param updateStatusRequestDto Client to update (required)
     * @return SuccessResponseDto
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _patchClient(Integer clienteId, UpdateStatusRequestDto updateStatusRequestDto) {
        return ResponseEntity.ok().body(successResponseMapper
                .successResponseToDto(clienteUseCase.updateStatusClient(clienteId, clientesRestMapper.updateStatusRequestToDomain(updateStatusRequestDto))));
    }

    /**
     * @param clienteId Client id (required)
     * @param clientDto Client to update (required)
     * @return ClientDto
     */
    @Override
    public ResponseEntity<ClientDto> _putClient(Integer clienteId, ClientDto clientDto) {
        return ResponseEntity.ok().body(clientesRestMapper
                .clientToDto(clienteUseCase.updateClient(clienteId, clientesRestMapper.clienteToDomain(clientDto))));
    }

}
