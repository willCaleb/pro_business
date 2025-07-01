package com.will.caleb.business.model.records.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FinancialExpenseResponse(Integer id,
                                       @JsonFormat(pattern = "yyyy-MM-dd")
                                       LocalDateTime data,
                                       BigDecimal valor,
                                       String categoria,
                                       String descricao,
                                       String fornecedorRelacionado,
                                       boolean recorrente,
                                       @JsonFormat(pattern = "yyyy-MM-dd")
                                       LocalDateTime createdAt,
                                       @JsonFormat(pattern = "yyyy-MM-dd")
                                       LocalDateTime updatedAt) implements AbstractResponseDTO {
}
