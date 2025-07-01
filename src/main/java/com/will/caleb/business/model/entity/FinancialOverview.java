package com.will.caleb.business.model.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FinancialOverview extends AbstractEntity{

    private Integer id;

    private BigDecimal saldoAtual;

    private BigDecimal receitaTotalMes;

    private BigDecimal despesaTotalMes;

    private BigDecimal lucroLiquidoMes;

    private BigDecimal saldoProjetadoMes;

    private BigDecimal ticketMedio;

    private BigDecimal margemLucro;

    private BigDecimal custoOperacional;

    private BigDecimal pontoEquilibrio;

    private BigDecimal grauEndividamento;
}
