package dev.guillermosg.msclients.infrastructure.adapters.input.rest.errorhandlers;


import dev.guillermosg.msclients.application.ports.dto.ErrorDTO;
import dev.guillermosg.msclients.domain.exception.AccountNotExistException;
import dev.guillermosg.msclients.domain.exception.ClientNotExistException;
import dev.guillermosg.msclients.domain.exception.UnavailableBalanceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(AccountNotExistException.class)
    public ResponseEntity<Object> handleCoinNotExistException(Locale locale) {
        String errorMessage = messageSource.getMessage("message.exception.accountnotexist", null, locale);
        return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder().codResponse(AccountNotExistException.ERROR_CODE)
                        .desResponse(errorMessage).build());
    }

    @ExceptionHandler(UnavailableBalanceException.class)
    public ResponseEntity<Object> handleUnavailableBalanceException(Locale locale) {
        String errorMessage = messageSource.getMessage("message.exception.unavailablebalance", null, locale);
        return ResponseEntity
               .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder().codResponse(UnavailableBalanceException.ERROR_CODE)
                        .desResponse(errorMessage).build());
    }

    @ExceptionHandler(ClientNotExistException.class)
    public ResponseEntity<Object> handleClientNotExistException(Locale locale) {
        String errorMessage = messageSource.getMessage("message.exception.clientnotexist", null, locale);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorDTO.builder().codResponse(ClientNotExistException.ERROR_CODE)
                        .desResponse(errorMessage).build());
    }

}
