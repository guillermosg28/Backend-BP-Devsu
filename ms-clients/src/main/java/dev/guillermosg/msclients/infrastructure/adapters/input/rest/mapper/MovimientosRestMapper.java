package dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper;

import dev.guillermosg.msclients.domain.model.MovimientoRequest;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.MovementRequestDto;
import org.mapstruct.Mapper;

/**
 * The interface MovimientosRestMapper
 */
@Mapper
public interface MovimientosRestMapper {

    MovimientoRequest movementToDto(MovementRequestDto dto);
}
