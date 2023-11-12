package dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.mapper;


import dev.guillermosg.msmovements.domain.model.MovimientoRequest;
import dev.guillermosg.msmovements.domain.model.MovimientosResponse;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity.MovimientoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The interface Movimiento persistence mapper.
 */

@Mapper
public interface MovimientoPersistenceMapper {

    @Mapping(target = "cuenta.id", source = "cuentaId")
    MovimientoEntity toMovimiento(MovimientoRequest movimiento);

    MovimientosResponse toMovimientosResponse(MovimientoEntity movimiento);
}
