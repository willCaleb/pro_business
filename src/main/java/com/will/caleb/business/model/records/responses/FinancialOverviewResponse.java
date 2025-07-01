package com.will.caleb.business.model.records.responses;

import java.math.BigDecimal;

public record FinancialOverviewResponse(BigDecimal saldoAtual,
                                        BigDecimal receitaTotalMes,
                                        BigDecimal despesaTotalMes,
                                        BigDecimal lucroLiquidoMes,
                                        BigDecimal saldoProjetadoMes,
                                        BigDecimal ticketMedio,
                                        BigDecimal margemLucro,
                                        BigDecimal custoOperacional,
                                        BigDecimal pontoEquilibrio,
                                        BigDecimal grauEndividamento) implements AbstractResponseDTO {
}
