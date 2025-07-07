package com.will.caleb.business.repository.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.caleb.business.model.records.responses.artificial_intelligence.FinancialOverviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomFinancialOverviewRepository {

    private final NamedParameterJdbcTemplate jdbc;

    private final ObjectMapper mapper;

    public FinancialOverviewResponse getFinancialOvewview(Integer enterpriseId) {

        try {
            return jdbc.queryForObject(getSqlString(), getParams(enterpriseId),
                    (rs, rowNum) -> {
                        String detalhesJson = rs.getString("detalhes_receitas");
                        List<FinancialOverviewResponse.ReceitaDetalhada> detalhes = Collections.emptyList();

                        if (detalhesJson != null && !detalhesJson.isBlank()) {
                            try {
                                detalhes = mapper.readValue(detalhesJson,
                                        new TypeReference<List<FinancialOverviewResponse.ReceitaDetalhada>>() {});
                            } catch (JsonProcessingException e) {
                                // logar ou tratar conforme necessário
                                e.printStackTrace();
                            }
                        }

                        return new FinancialOverviewResponse(
                                rs.getBigDecimal("saldo_atual"),
                                rs.getBigDecimal("receita_total_mes"),
                                rs.getBigDecimal("despesa_total_mes"),
                                rs.getBigDecimal("lucro_total_mes"),
                                rs.getBigDecimal("saldo_projetado"),
                                rs.getBigDecimal("ticket_medio"),
                                rs.getBigDecimal("margem_lucro"),
                                rs.getBigDecimal("custo_operacional"),
                                rs.getBigDecimal("ponto_equilibrio"),
                                rs.getBigDecimal("grau_endividamento"),
                                null,
                                LocalDate.now(),
                                detalhes
                        );
                    });

        }catch (Exception e) {
            return null;
        }
    }

    private static MapSqlParameterSource getParams(Integer enterpriseId) {
        return new MapSqlParameterSource()
                .addValue("enterpriseID", enterpriseId);
    }

    private String getSqlString() {
        return """
                with receita_total as (
                            select\s
                                sum(rev.valor) as receita_total,
                                round(sum(rev.valor) / count(rev.id), 2) as ticket_medio
                            from bus_financial_revenue rev
                            where rev.enterprise_id = :enterpriseID
                              and rev.categoria in ('Vendas')
                        ),
                        despesa_total as (
                            select sum(exp.valor) as despesa_total
                            from bus_financial_expense exp
                            where exp.enterprise_id = :enterpriseID
                        ),
                        custo_operacional as (
                            select sum(valor) as custo_operacional
                            from bus_financial_expense
                            where enterprise_id = :enterpriseID
                              and categoria in ('Aluguel', 'Folha de pagamento', 'Impostos', 'Fornecedores', 'Marketing')
                        ),
                        dados_mes as (
                            select\s
                                coalesce(sum(rev.valor), 0) as receita_total_mes,
                                coalesce(sum(exp.valor), 0) as despesa_total_mes
                            from bus_financial_revenue rev
                            left join bus_financial_expense exp\s
                                   on rev.enterprise_id = exp.enterprise_id
                            where date_trunc('month', rev.data) = date_trunc('month', current_date)
                              and date_trunc('month', exp.data) = date_trunc('month', current_date)
                              and rev.enterprise_id = :enterpriseID
                        ),
                        metas as (
                            select\s
                                met.meta_despesa_mensal as meta_despesa,
                                met.meta_receita_mensal as meta_receita
                            from bus_financial_goal met
                            where extract(month from now()) = met.mes
                              and met.enterprise_id = :enterpriseID
                        ),
                        receitas_descricao as (
                            select\s
                                jsonb_agg(
                          jsonb_build_object(
                            'descricao', rev.descricao,
                            'valor', rev.valor,
                            'data', to_char(rev.data, 'YYYY-MM-DD'),
                            'categoria', rev.categoria
                          )
                        ) as receitas
                            from bus_financial_revenue rev
                            where rev.enterprise_id = :enterpriseID
                              and rev.descricao is not null
                        )
                        select\s
                            coalesce(rt.receita_total, 0) as receita_total,
                            coalesce(dt.despesa_total, 0) as despesa_total,
                            coalesce(round((rt.receita_total - dt.despesa_total) / nullif(rt.receita_total, 0) * 100, 2), 0) as margem_lucro,
                            coalesce(round(dt.despesa_total * 100 / nullif(((rt.receita_total - dt.despesa_total) / rt.receita_total * 100), 0), 2), 0) as ponto_equilibrio,
                            coalesce(co.custo_operacional, 0) as custo_operacional,
                            coalesce(rt.receita_total, 0) - coalesce(dt.despesa_total, 0) as saldo_atual,
                            round((coalesce(dt.despesa_total, 0) / coalesce(rt.receita_total, 1)) * 100, 2) as grau_endividamento,
                            round(coalesce(rt.ticket_medio, 0), 2) as ticket_medio,
                            coalesce(dm.receita_total_mes, 0) as receita_total_mes,
                            coalesce(dm.despesa_total_mes, 0) as despesa_total_mes,
                            dm.receita_total_mes - dm.despesa_total_mes as lucro_total_mes,
                            coalesce(met.meta_despesa, 0) as meta_despesa,
                            coalesce(met.meta_receita, 0) as meta_receita,
                            (coalesce(met.meta_receita, 0) - coalesce(met.meta_despesa, 0)) as saldo_projetado,
                            rd.receitas as detalhes_receitas
                        from receita_total rt
                        join despesa_total dt on 1=1
                        join custo_operacional co on 1=1
                        join dados_mes dm on 1=1
                        left join metas met on 1=1
                        left join receitas_descricao rd on 1=1
                """;
    }
}
