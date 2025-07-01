package com.will.caleb.business.model.records.requests;

public record ProdutoEstoqueRequest(int quantidade,
                                    int minimo,
                                    int maximo) implements AbstractRequestDTO{
}
