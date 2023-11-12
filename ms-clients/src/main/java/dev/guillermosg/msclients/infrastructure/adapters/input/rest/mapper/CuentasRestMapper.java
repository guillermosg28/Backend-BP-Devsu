package dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper;

import dev.guillermosg.msclients.domain.model.AccountsResponse;
import dev.guillermosg.msclients.domain.model.Cuenta;
import dev.guillermosg.msclients.domain.model.CuentaRequest;
import dev.guillermosg.msclients.domain.model.UpdateStatusAccountRequest;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.AccountDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.AccountRequestDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.AccountsResponseDto;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.UpdateStatusAccountRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * The interface CuentasRestMapper
 */
@Mapper
public interface CuentasRestMapper {

    CuentaRequest accountToDto(AccountRequestDto dto);

    AccountsResponseDto accountsToDto(AccountsResponse domain);

    @Mapping(target = "cuentaId", source = "id")
    AccountDto accountToDto(Cuenta domain);

    UpdateStatusAccountRequest updateStatusAccountRequestDomain(UpdateStatusAccountRequestDto dto);
}
