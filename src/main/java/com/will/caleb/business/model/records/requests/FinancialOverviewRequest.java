package com.will.caleb.business.model.records.requests;

import java.math.BigDecimal;

public record FinancialOverviewRequest( BigDecimal saldoAtual,
                                        BigDecimal receitaTotalMes,
                                        BigDecimal despesaTotalMes,
                                        BigDecimal lucroLiquidoMes,
                                        BigDecimal saldoProjetadoMes,
                                        BigDecimal ticketMedio,
                                        BigDecimal margemLucro,
                                        BigDecimal custoOperacional,
                                        BigDecimal pontoEquilibrio,
                                        BigDecimal grauEndividamento) implements AbstractRequestDTO{
}
