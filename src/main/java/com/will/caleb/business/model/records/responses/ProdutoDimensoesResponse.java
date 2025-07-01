package com.will.caleb.business.model.records.responses;

import java.math.BigDecimal;

public record ProdutoDimensoesResponse(BigDecimal altura,
                                       BigDecimal largura,
                                       BigDecimal profundidade) implements AbstractResponseDTO {
}
