package dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper;


import dev.guillermosg.msclients.domain.model.EstadoCuentaResponse;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.EstadoCuentaResponseDto;
import org.mapstruct.Mapper;

/**
 * The interface SuccessResponseMapper
 */
@Mapper
public interface ReporteMapper {

    EstadoCuentaResponseDto estadoCuentaToDto(EstadoCuentaResponse domain);
}
