package com.will.caleb.business.controller.financial;

import com.will.caleb.business.model.classes.DashboardFinanceiroResponse;
import com.will.caleb.business.service.FinancialAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(FinancialAnalyticsController.PATH)
@RequiredArgsConstructor
public class FinancialAnalyticsController extends AbstractFinancialController{
    public static final String PATH = FINANCIAL_PATH + "/analytics";

    private final FinancialAnalyticsService financialAnalyticsService;

    @GetMapping
    public DashboardFinanceiroResponse getAnalytics(@RequestParam("initialDate") @DateTimeFormat(pattern = "yyyy-MM-dd")Date initialDate,
                                                    @RequestParam("finalDate")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date finalDate){
        return financialAnalyticsService.findAnalytics(initialDate, finalDate);
    }

}
