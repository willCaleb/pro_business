package com.will.caleb.business.model.records.responses;

public record ProdutoEstoqueResponse(Integer quantidade,
                                     Integer minimo,
                                     Integer maximo) implements AbstractResponseDTO {
}
