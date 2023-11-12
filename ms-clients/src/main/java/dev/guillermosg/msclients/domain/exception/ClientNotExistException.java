package dev.guillermosg.msclients.domain.exception;

public class ClientNotExistException extends RuntimeException{

    public static final String ERROR_CODE = "003";
    private static final long serialVersionUID = 1L;

    public ClientNotExistException() {
        super();
    }
}
