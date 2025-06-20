package com.will.caleb.business.exception;

public enum EnumCustomException {

    DEFAULT_CUSTOM_EXCEPTION("Erro! Entre em contato com o administrador"),
    CLIENT_DUPLICATED_EMAIL("O email {0} já está cadastrado para o cliente {1}"),
    OBJECTS_ARE_DIFFERENTS("Os objetos são diferentes e não podem ser comparados"),
    CLIENT_DUPLICATED_DOCUMENT("O documento {0} já está cadastrado para o cliente {1}"),
    ENTERPRISE_DUPLICATED_EMAIL("Já existe cadastro de empresa com o e-mail informado"),
    ENTERPRISE_DUPLICATED_CNPJ("Já existe cadastro de empresa com o CNPJ informado"),
    USER_DUPLICATED_EMAIL("O email informado já está cadastrado"),
    USER_EMAIL_REQUIRED("Emai");

    private final String message;

     EnumCustomException(String message) {
        this.message = message;
    }

    public String getMessage() {
         return this.message;
    }

}
