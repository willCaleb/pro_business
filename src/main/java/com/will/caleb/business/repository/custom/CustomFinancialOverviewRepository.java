package com.will.caleb.business.repository.custom;

import com.will.caleb.business.model.records.responses.FinancialOverviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomFinancialOverviewRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public FinancialOverviewResponse getFinancialOvewview(Integer enterpriseId) {
        return jdbc.queryForObject(getSqlString(), getParams(enterpriseId),
                (rs, rowNum) -> new FinancialOverviewResponse(
                        rs.getBigDecimal("saldo_atual"),
                        rs.getBigDecimal("receita_total_mes"),
                        rs.getBigDecimal("despesa_total_mes"),
                        rs.getBigDecimal("lucro_total_mes"),
                        rs.getBigDecimal("saldo_projetado"),
                        rs.getBigDecimal("ticket_medio"),
                        rs.getBigDecimal("margem_lucro"),
                        rs.getBigDecimal("custo_operacional"),
                        rs.getBigDecimal("ponto_equilibrio"),
                        rs.getBigDecimal("grau_endividamento")
                ));
    }

    private static MapSqlParameterSource getParams(Integer enterpriseId) {
        return new MapSqlParameterSource()
                .addValue("enterpriseID", enterpriseId);
    }

    private String getSqlString() {
        return """
                with receita_total as (
                	select sum(rev.valor) as receita_total,
                		   round(sum(rev.valor) / count(rev.id), 2) as ticket_medio
                	  from bus_financial_revenue rev
                	 where 1 = 1
                	   and rev.enterprise_id = :enterpriseID
                ), despesa_total as (
                	select sum(exp.valor) as despesa_total
                	  from bus_financial_expense exp
                	 where 1 = 1
                	   and exp.enterprise_id = :enterpriseID
                ),custo_operacional AS (
                  SELECT SUM(valor) AS custo_operacional
                    FROM bus_financial_expense
                   WHERE 1 = 1
                     and enterprise_id = :enterpriseID
                     and categoria in ('Aluguel', 'Folha de pagamento', 'Impostos', 'Fornecedores', 'Marketing')
                  ), dados_mes as (
                  	select coalesce(sum(rev.valor), 0) as receita_total_mes,
                  	       coalesce(sum(exp.valor), 0) as despesa_total_mes
                  	  from bus_financial_revenue rev
                  	  left join bus_financial_expense exp on rev.enterprise_id = exp.enterprise_id
                  	 where DATE_TRUNC('month', rev.data) = DATE_TRUNC('month', CURRENT_DATE)
                  	   and DATE_TRUNC('month', exp.data) = DATE_TRUNC('month', CURRENT_DATE)
                  ), metas as (
                  	select met.meta_despesa_mensal as meta_despesa,
                  	       met.meta_receita_mensal as meta_receita
                  	  from bus_financial_goal met \s
                  	 where extract (month from now()) = met.mes\s
                  	   and met.enterprise_id = :enterpriseID
                  )
                 select rt.receita_total,
                       dt.despesa_total,
                       round(((rt.receita_total - dt.despesa_total) / rt.receita_total) * 100, 2) as margem_lucro,
                       round(dt.despesa_total * 100 / (((rt.receita_total - dt.despesa_total) / rt.receita_total) * 100), 2) as ponto_equilibrio,
                       co.custo_operacional,
                       rt.receita_total - dt.despesa_total as saldo_atual,
                       round((dt.despesa_total / rt.receita_total) * 100, 2) as grau_endividamento,
                       rt.ticket_medio as ticket_medio,
                       dm.receita_total_mes,
                       dm.despesa_total_mes,
                       dm.receita_total_mes - dm.despesa_total_mes as lucro_total_mes,
                       met.meta_despesa as meta_despesa,
                       met.meta_receita as meta_receita,
                       met.meta_receita - met.meta_despesa as saldo_projetado
                from receita_total rt, despesa_total dt, custo_operacional co, dados_mes dm, metas met
                """;
    }
}
