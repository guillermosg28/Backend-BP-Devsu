package dev.guillermosg.msclients.domain.exception;

public class UnavailableBalanceException extends RuntimeException{

        public static final String ERROR_CODE = "002";
        private static final long serialVersionUID = 1L;

        public UnavailableBalanceException() {
            super();
        }
}
