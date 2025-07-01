package com.will.caleb.business.model.classes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DashboardFinanceiroResponse(
    List<EvolucaoMensal> evolucaoMensal,
    List<DistribuicaoDespesa> distribuicaoDespesas,
    List<FluxoCaixa> fluxoCaixa,
    ComparativoMetas comparativoMetas
) {

    public record EvolucaoMensal(
        String mes,
        BigDecimal receita,
        BigDecimal despesa,
        BigDecimal lucro
    ) {}

    public record DistribuicaoDespesa(
        String categoria,
        BigDecimal valor,
        BigDecimal percentual
    ) {}

    public record FluxoCaixa(
        LocalDate data,
        BigDecimal saldo,
        BigDecimal variacao
    ) {}

    public record ComparativoMetas(
        BigDecimal receitaVsMeta,
        BigDecimal despesaVsMeta,
        BigDecimal metaReceitaMensal,
        BigDecimal metaDespesaMensal,
        BigDecimal receitaAtual,
        BigDecimal despesaAtual
    ) {}
}
