package com.will.caleb.business.model.records.requests;

import java.math.BigDecimal;

public record ProdutoDimensoesRequest(BigDecimal altura,
                                      BigDecimal largura,
                                      BigDecimal profundidade) implements AbstractRequestDTO{
}
