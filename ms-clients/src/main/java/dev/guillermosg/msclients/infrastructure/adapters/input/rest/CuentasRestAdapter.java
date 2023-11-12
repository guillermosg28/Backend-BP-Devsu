package dev.guillermosg.msclients.infrastructure.adapters.input.rest;

import dev.guillermosg.msclients.application.ports.input.CuentaUseCase;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.data.*;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper.CuentasRestMapper;
import dev.guillermosg.msclients.infrastructure.adapters.input.rest.mapper.SuccessResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Cuentas rest adapter.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/")
public class CuentasRestAdapter implements CuentasApi{

    private final CuentasRestMapper cuentasRestMapper;
    private final CuentaUseCase cuentasUseCase;
    private final SuccessResponseMapper successResponseMapper;

    /**
     * @param accountRequestDto Account to create (required)
     * @return SuccessResponseDto
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _createAccount(AccountRequestDto accountRequestDto) {

        return ResponseEntity.ok().body(successResponseMapper
                .successResponseToDto(cuentasUseCase.createAccount(cuentasRestMapper.accountToDto(accountRequestDto))));
    }


    /**
     * @return
     */
    @Override
    public ResponseEntity<AccountsResponseDto> _listAccounts() {

        return ResponseEntity.ok().body(cuentasRestMapper
                .accountsToDto(cuentasUseCase.getAccounts()));
    }

    /**
     * @param cuentaId                      Account id (required)
     * @param updateStatusAccountRequestDto Client to update (required)
     * @return
     */
    @Override
    public ResponseEntity<SuccessResponseDto> _patchAccount(Integer cuentaId, UpdateStatusAccountRequestDto updateStatusAccountRequestDto) {
        return ResponseEntity.ok().body(successResponseMapper
                .successResponseToDto(cuentasUseCase
                        .updateStatusAccount(
                                cuentaId,
                                cuentasRestMapper.updateStatusAccountRequestDomain(updateStatusAccountRequestDto))));
    }

}
