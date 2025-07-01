package com.will.caleb.business.model.records.requests;

import java.math.BigDecimal;
import java.util.Date;

public record FinancialExpenseRequest(
        Integer id,
         Date data,
         BigDecimal valor,
         String categoria,
         String descricao,
         String fornecedorRelacionado,
         boolean recorrente,
         Date createdAt,
         Date updatedAt) implements AbstractRequestDTO{
}
