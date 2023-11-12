package dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.mapper;

import dev.guillermosg.msmovements.domain.model.Cuenta;
import dev.guillermosg.msmovements.infrastructure.adapters.output.persistence.entity.CuentaEntity;
import org.mapstruct.Mapper;

/**
 * The interface Cuenta persistence mapper.
 */

@Mapper
public interface CuentaPersistenceMapper {

    Cuenta toCuenta(CuentaEntity cuentaEntity);

}
