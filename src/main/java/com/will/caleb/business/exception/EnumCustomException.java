package com.will.caleb.business.exception;

public enum EnumCustomException {

    DEFAULT_CUSTOM_EXCEPTION("Erro! Entre em contato com o administrador"),
    CLIENT_DUPLICATED_EMAIL("O email {0} já está cadastrado para o cliente {1}"),
    OBJECTS_ARE_DIFFERENTS("Os objetos são diferentes e não podem ser comparados"),
    CLIENT_DUPLICATED_DOCUMENT("O documento {0} já está cadastrado para o cliente {1}"),
    ENTERPRISE_DUPLICATED_EMAIL("Já existe cadastro de empresa com o e-mail informado"),
    ENTERPRISE_DUPLICATED_CNPJ("Já existe cadastro de empresa com o CNPJ informado"),
    USER_DUPLICATED_EMAIL("Já existe um usuário cadastrado com o e-mail informado"),
    ENTERPRISE_ERROR_DATA_NOT_FOUND("Erro ao buscar dados da empresa"),
    FINANCIAL_REVENUE_NOT_FOUND("Ocorreu um erro ao buscar receita"),
    FINANCIAL_EXPENSE_NOT_FOUND("Não foi possível encontrar a despesa"),
    FINANCIAL_GOAL_NOT_FOUND("Não foi possível encontrar a meta"),
    LOGIN_TOKEN_EXPIRED("Token expirado! Faça login novamente no sistema"),
    USER_NOT_FOUND("Usuário não encontrado");


    private final String message;

     EnumCustomException(String message) {
        this.message = message;
    }

    public String getMessage() {
         return this.message;
    }

}
