package com.will.caleb.business.model.records.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public record FinancialGoalResponse(Integer id,
                                    BigDecimal metaReceitaMensal,
                                    BigDecimal metaDespesaMensal,
                                    Integer mes,
                                    Integer ano,
                                    @JsonFormat(pattern = "yyyy-MM-dd")
                                    Date createdAt,
                                    @JsonFormat(pattern = "yyyy-MM-dd")
                                    Date updatedAt) implements AbstractResponseDTO {
}
