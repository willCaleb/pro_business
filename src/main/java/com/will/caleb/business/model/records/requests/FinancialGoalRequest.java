package com.will.caleb.business.model.records.requests;


import java.math.BigDecimal;
import java.util.Date;

public record FinancialGoalRequest(Integer id,
                                   BigDecimal metaReceitaMensal,
                                   BigDecimal metaDespesaMensal,
                                   Integer mes,
                                   Integer ano,
                                   Date createdAt,
                                   Date updatedAt) implements AbstractRequestDTO{
}
