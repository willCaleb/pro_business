package com.will.caleb.business.model.records.requests;

import java.util.Date;

public record FinancialMovementRequest(Integer id,
                                       String tipo,
                                       String descricao,
                                       Date dataVencimento,
                                       String status,
                                       String client,
                                       String fornecedor) implements AbstractRequestDTO {
}
