package com.will.caleb.business.model.records.requests;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public record FinancialRevenueRequest(Integer id,
                                      Date data,
                                      BigDecimal valor,
                                      String categoria,
                                      String descricao,
                                      String clienteRelacionado,
                                      Date createdAt,
                                      Date updatedAt) implements AbstractRequestDTO{
}
