package com.will.caleb.business.model.records.responses;

import java.util.Date;

public record FinancialFutureMovementResponse(Integer id,
                                              String tipo,
                                              String descricao,
                                              Date dataVencimento,
                                              String status,
                                              String client,
                                              String fornecedor) implements AbstractResponseDTO{
}
