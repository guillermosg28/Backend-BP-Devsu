package dev.guillermosg.msclients.infrastructure.adapters.input.rest;


import dev.guillermosg.msclients.application.ports.input.MovimientoUseCase;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.MovementRequestDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.SuccessResponseDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper.MovimientosRestMapper;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper.SuccessResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Movimientos rest adapter.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class MovimientosRestAdapter implements MovimientosApi {

    private final MovimientosRestMapper movimientosRestMapper;
    private final MovimientoUseCase movimientoUseCase;
    private final SuccessResponseMapper successResponseMapper;

    /**
     * @param movementRequestDto Movement to create (required)
     * @return SuccessResponseDto
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _createMovement(MovementRequestDto movementRequestDto) {
        return ResponseEntity.ok().body(successResponseMapper
                .successResponseToDto(movimientoUseCase.createMovement(movimientosRestMapper.movementToDto(movementRequestDto))));

    }


}
