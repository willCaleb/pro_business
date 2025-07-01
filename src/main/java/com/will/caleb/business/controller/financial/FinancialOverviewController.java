package com.will.caleb.business.controller.financial;

import com.will.caleb.business.controller.AbstractController;
import com.will.caleb.business.model.entity.FinancialOverview;
import com.will.caleb.business.model.records.responses.FinancialOverviewResponse;
import com.will.caleb.business.service.FinancialOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping(FinancialOverviewController.PATH)
public class FinancialOverviewController extends AbstractFinancialController {

    public static final String PATH = FINANCIAL_PATH + "/overview";

    private final FinancialOverviewService financialOverviewService;

    @GetMapping
    FinancialOverviewResponse getOverview() {
        return financialOverviewService.generateFinancialOverview();
    }

}
