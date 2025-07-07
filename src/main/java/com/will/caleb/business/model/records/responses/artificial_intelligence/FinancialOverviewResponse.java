package com.will.caleb.business.model.records.responses.artificial_intelligence;

import com.fasterxml.jackson.databind.JsonNode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FinancialOverviewResponse(BigDecimal saldoAtual,
                                        BigDecimal receitaTotalMes,
                                        BigDecimal despesaTotalMes,
                                        BigDecimal lucroLiquidoMes,
                                        BigDecimal saldoProjetadoMes,
                                        BigDecimal ticketMedio,
                                        BigDecimal margemLucro,
                                        BigDecimal custoOperacional,
                                        BigDecimal pontoEquilibrio,
                                        BigDecimal grauEndividamento,
                                        RespostaIA respostaIA,
                                        LocalDate dataAtual,
                                        List<ReceitaDetalhada> receitasDetalhadas) implements AbstractArtificialIntelligenceResponse {
    public record RespostaIA(
            String titulo,
            String situacao,
            List<String> pontosFortes,
            List<String> pontosFracos,
            List<String> riscos,
            List<String> oportunidades,
            List<String> acoesEstrategicas

    ) {}
    public record ReceitaDetalhada(
            String descricao,
            BigDecimal valor,
            LocalDate data,
            String categoria
    ) {}
}
