package com.will.caleb.business.controller.financial;


import com.will.caleb.business.model.entity.FinancialExpense;
import com.will.caleb.business.model.entity.FinancialRevenue;
import com.will.caleb.business.model.records.responses.FinancialExpenseResponse;
import com.will.caleb.business.model.records.responses.FinancialRevenueResponse;
import com.will.caleb.business.pattern.PageableBean;
import com.will.caleb.business.service.FinancialExpenseService;
import com.will.caleb.business.service.FinancialRevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(FinancialFutureController.PATH)
public class FinancialFutureController extends AbstractFinancialController{

    public static final String PATH = FINANCIAL_PATH + "/future";
    private final FinancialRevenueService financialRevenueService;
    private final FinancialExpenseService financialExpenseService;

    @GetMapping("/revenues")
    public Page<FinancialRevenueResponse> findFutureRevenues(Pageable pageable) {
        Page<FinancialRevenue> financialRevenues = financialRevenueService.findAllFuture(pageable);
        List<FinancialRevenueResponse> responses = new ArrayList<>();

        financialRevenues.forEach(rev -> responses.add(toResponse(rev, FinancialRevenueResponse.class)));

        return new PageableBean<FinancialRevenueResponse>()
                .withPageable(pageable)
                .withContent(responses)
                .withTotal(financialRevenues.getTotalElements())
                .getPaged();

    }

    @GetMapping("/expenses")
    public Page<FinancialExpenseResponse> findFutureExpenses(Pageable pageable) {
        Page<FinancialExpense> financialExpenses = financialExpenseService.findAllFuture(pageable);
        List<FinancialExpenseResponse> responses = new ArrayList<>();

        financialExpenses.forEach(exp -> responses.add(toResponse(exp, FinancialExpenseResponse.class)));

        return new PageableBean<FinancialExpenseResponse>()
                .withPageable(pageable)
                .withContent(responses)
                .withTotal(financialExpenses.getTotalElements())
                .getPaged();
    }

}
