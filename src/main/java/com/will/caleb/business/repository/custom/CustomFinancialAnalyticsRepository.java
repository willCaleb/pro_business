package com.will.caleb.business.repository;

import com.will.caleb.business.model.classes.DashboardFinanceiroResponse;
import com.will.caleb.business.pattern.enums.EnumMonth;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FinancialAnalyticsCustomRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public DashboardFinanceiroResponse buscarDashboard(Date initialDate, Date finalDate, Integer enterpriseId) {

        MapSqlParameterSource params = getParams(initialDate, finalDate, enterpriseId);

        return new DashboardFinanceiroResponse(getEvolucaoMensal(params), getDespesasPorCategorias(params), getFluxoCaixaPorMes(params), getComparativoMetas(params));
    }

    private List<DashboardFinanceiroResponse.FluxoCaixa> getFluxoCaixaPorMes(MapSqlParameterSource params) {
        return jdbc.query(getFluxoCaixaQuery(), params,
                (rs, rowNum) -> new DashboardFinanceiroResponse.FluxoCaixa(
                        rs.getDate("data").toLocalDate(),
                        rs.getBigDecimal("saldo"),
                        rs.getBigDecimal("variacao")
                )
        );
    }

    private List<DashboardFinanceiroResponse.DistribuicaoDespesa> getDespesasPorCategorias(MapSqlParameterSource params) {
        return jdbc.query(getByCategoryResumeQuery(), params,
                (rs, rowNum) -> new DashboardFinanceiroResponse.DistribuicaoDespesa(
                        rs.getString("categoria"),
                        rs.getBigDecimal("valor"),
                        rs.getBigDecimal("percentual")
                )
        );
    }

    private List<DashboardFinanceiroResponse.EvolucaoMensal> getEvolucaoMensal(MapSqlParameterSource params) {
        return jdbc.query(getByMonthResumeQuery(), params,
                (rs, rowNum) -> new DashboardFinanceiroResponse.EvolucaoMensal(
                        EnumMonth.getLocalValue(rs.getString("mes").trim()),
                        rs.getBigDecimal("receita"),
                        rs.getBigDecimal("despesa"),
                        rs.getBigDecimal("lucro")
                )
        );
    }

    public DashboardFinanceiroResponse.ComparativoMetas getComparativoMetas(MapSqlParameterSource params) {
        String sql = getComparativoMetasSql();

        return jdbc.queryForObject(sql, params, (rs, rowNum) -> new DashboardFinanceiroResponse.ComparativoMetas(
                rs.getBigDecimal("receitaAtual"),
                rs.getBigDecimal("despesaAtual"),
                rs.getBigDecimal("meta_receita_mensal"),
                rs.getBigDecimal("meta_despesa_mensal"),
                rs.getBigDecimal("receitaVsMeta"),
                rs.getBigDecimal("despesaVsMeta")
        ));
    }

    private String getComparativoMetasSql() {
        return """
                WITH receita_despesa AS (
                    SELECT
                        (SELECT COALESCE(SUM(valor), 0)
                         FROM bus_financial_revenue
                         WHERE enterprise_id = :enterpriseId
                           AND data BETWEEN :initialDate AND :finalDate
                        ) AS receitaAtual,
                        (SELECT COALESCE(SUM(valor), 0)
                         FROM bus_financial_expense
                         WHERE enterprise_id = :enterpriseId
                           AND data BETWEEN :initialDate AND :finalDate
                        ) AS despesaAtual
                ),
                metas AS (
                    SELECT
                        meta_receita_mensal,
                        meta_despesa_mensal
                    FROM bus_financial_goal
                    where  mes >= extract(month from cast(:initialDate as timestamp))::int
                      and mes <= EXTRACT(MONTH FROM CAST(:finalDate AS TIMESTAMP))::int
                      AND ano = EXTRACT(YEAR FROM CAST(:finalDate AS TIMESTAMP))::int
                    LIMIT 1
                )
                SELECT
                    r.receitaAtual,
                    r.despesaAtual,
                    m.meta_receita_mensal,
                    m.meta_despesa_mensal,
                    ROUND((r.receitaAtual * 100.0 / NULLIF(m.meta_receita_mensal, 0)), 2) AS receitaVsMeta,
                    ROUND((r.despesaAtual * 100.0 / NULLIF(m.meta_despesa_mensal, 0)), 2) AS despesaVsMeta
                FROM receita_despesa r
                left JOIN metas m on true
                """;
    }

    private String getByCategoryResumeQuery() {
        return """
                SELECT
                  categoria,
                  SUM(valor) AS valor,
                  ROUND((SUM(valor) * 100.0) / (SELECT SUM(valor) FROM bus_financial_expense WHERE enterprise_id = :enterpriseId), 2) AS percentual
                FROM
                  bus_financial_expense
                WHERE
                  enterprise_id = :enterpriseId
                  AND data BETWEEN :initialDate AND :finalDate
                GROUP BY
                  categoria
                ORDER BY
                  percentual DESC
                """;
    }

    private static MapSqlParameterSource getParams(Date initialDate, Date finalDate, Integer enterpriseId) {
        return new MapSqlParameterSource()
                .addValue("enterpriseId", enterpriseId)
                .addValue("initialDate", initialDate)
                .addValue("finalDate", finalDate);
    }

    private static String getByMonthResumeQuery() {
        return  """
        SELECT
          TO_CHAR(COALESCE(r.mes, e.mes), 'Month YYYY') AS mes,
          COALESCE(r.receita, 0) AS receita,
          COALESCE(e.despesa, 0) AS despesa,
          COALESCE(r.receita, 0) - COALESCE(e.despesa, 0) AS lucro
        FROM
          (
            SELECT
              DATE_TRUNC('month', rev.data) AS mes,
              SUM(rev.valor) AS receita
            FROM bus_financial_revenue rev
            WHERE rev.enterprise_id = :enterpriseId
              AND rev.data BETWEEN :initialDate AND :finalDate
            GROUP BY DATE_TRUNC('month', rev.data)
          ) r
        FULL OUTER JOIN
          (
            SELECT
              DATE_TRUNC('month', exp.data) AS mes,
              SUM(exp.valor) AS despesa
            FROM bus_financial_expense exp
            WHERE exp.enterprise_id = :enterpriseId
              AND exp.data BETWEEN :initialDate AND :finalDate
            GROUP BY DATE_TRUNC('month', exp.data)
          ) e ON r.mes = e.mes
        ORDER BY COALESCE(r.mes, e.mes)
    """;
    }

    private String getFluxoCaixaQuery() {
        return """
                WITH meses AS (
                  SELECT DATE_TRUNC('month', d)::DATE + INTERVAL '1 month - 1 day' AS data_fim_mes
                  FROM generate_series(:initialDate::date, :finalDate::date, INTERVAL '1 month') d
                ),
                receitas AS (
                  SELECT DATE_TRUNC('month', data) AS mes, SUM(valor) AS receita
                  FROM bus_financial_revenue
                  WHERE enterprise_id = :enterpriseId
                    AND data BETWEEN :initialDate AND :finalDate
                  GROUP BY DATE_TRUNC('month', data)
                ),
                despesas AS (
                  SELECT DATE_TRUNC('month', data) AS mes, SUM(valor) AS despesa
                  FROM bus_financial_expense
                  WHERE enterprise_id = :enterpriseId
                    AND data BETWEEN :initialDate AND :finalDate
                  GROUP BY DATE_TRUNC('month', data)
                ),
                saldo_mensal AS (
                  SELECT
                    m.data_fim_mes AS data,
                    COALESCE(r.receita, 0) AS receita,
                    COALESCE(d.despesa, 0) AS despesa,
                    COALESCE(r.receita, 0) - COALESCE(d.despesa, 0) AS variacao
                  FROM meses m
                  LEFT JOIN receitas r ON DATE_TRUNC('month', m.data_fim_mes) = r.mes
                  LEFT JOIN despesas d ON DATE_TRUNC('month', m.data_fim_mes) = d.mes
                ),
                saldo_acumulado AS (
                  SELECT
                    data,
                    variacao,
                    SUM(variacao) OVER (ORDER BY data) AS saldo
                  FROM saldo_mensal
                )
                SELECT
                  data,
                  saldo,
                  variacao
                FROM saldo_acumulado
                ORDER BY data;
                """;
    }

}
