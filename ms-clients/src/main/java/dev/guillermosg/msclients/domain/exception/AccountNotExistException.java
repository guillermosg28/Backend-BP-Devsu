package dev.guillermosg.msclients.domain.exception;

public class AccountNotExistException extends RuntimeException {

    public static final String ERROR_CODE = "001";
    private static final long serialVersionUID = 1L;

    public AccountNotExistException() {
        super();
    }
}
