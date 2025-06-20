package com.will.caleb.business.exception;

public class CustomException extends RuntimeException{

    @Deprecated
    public CustomException() {
        super("Erro! Entre em contato com o administrador");
    }

    @Deprecated
    public CustomException(String exceptionString) {
        super(exceptionString);
    }

    public CustomException(EnumCustomException customException) {
        this(customException.getMessage());
    }

    public CustomException(EnumCustomException customException, Object... args) {
        this(MessageConverter.convert(customException, args));
    }

    @Deprecated
    public CustomException(String message, Object... args) {
        this(MessageConverter.convert(message, args));
    }

}
