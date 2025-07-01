package com.will.caleb.business.controller;

import com.will.caleb.business.model.entity.FinancialOverview;
import com.will.caleb.business.model.records.converters.FinancialOverviewMapper;
import com.will.caleb.business.model.records.responses.FinancialOverviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping(FinancialOverviewController.PATH)
public class FinancialOverviewController {

    public static final String PATH = "${api.prefix.v1}/financial/overview";

    private final FinancialOverviewMapper financialOverviewMapper;

    @GetMapping
    FinancialOverviewResponse getOverview() {
        FinancialOverview overview = new FinancialOverview();

        overview.setPontoEquilibrio(new BigDecimal("30000"));
        overview.setMargemLucro(new BigDecimal("30"));
        overview.setSaldoAtual(new BigDecimal("350700"));
        overview.setCustoOperacional(new BigDecimal("10000"));
        overview.setGrauEndividamento(new BigDecimal("11"));
        overview.setTicketMedio(new BigDecimal("475"));
        overview.setDespesaTotalMes(new BigDecimal("74000"));
        overview.setLucroLiquidoMes(new BigDecimal("1450"));
        overview.setReceitaTotalMes(new BigDecimal("478500"));
        overview.setSaldoProjetadoMes(new BigDecimal("1200"));

        return financialOverviewMapper.toResponse(overview);
    }

}
