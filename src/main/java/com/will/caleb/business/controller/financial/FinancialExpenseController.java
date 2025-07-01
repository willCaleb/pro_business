package com.will.caleb.business.controller.financial;

import com.will.caleb.business.controller.AbstractController;
import com.will.caleb.business.model.entity.FinancialExpense;
import com.will.caleb.business.model.records.requests.FinancialExpenseRequest;
import com.will.caleb.business.model.records.responses.FinancialExpenseResponse;
import com.will.caleb.business.pattern.PageableBean;
import com.will.caleb.business.service.FinancialExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(FinancialExpenseController.PATH)
public class FinancialExpenseController extends AbstractFinancialController {


    public static final String PATH = FINANCIAL_PATH + "/expense";

    private final FinancialExpenseService financialExpenseService;

    @PostMapping("/include")
    public FinancialExpenseResponse include(@RequestBody FinancialExpenseRequest request) {
        FinancialExpense expense = toEntity(request, FinancialExpense.class);

        return toResponse(financialExpenseService.include(expense), FinancialExpenseResponse.class);
    }

    @PutMapping("/edit")
    public FinancialExpenseResponse edit(@RequestBody FinancialExpenseRequest request) {
        FinancialExpense expense = toEntity(request, FinancialExpense.class);

        return toResponse(financialExpenseService.edit(expense), FinancialExpenseResponse.class);
    }

    @GetMapping("/list")
    public Page<FinancialExpenseResponse> list(Pageable pageable) {

        Page<FinancialExpense> financialExpenses = financialExpenseService.list(pageable);
        List<FinancialExpenseResponse> responseList = new ArrayList<>();

        financialExpenses.forEach(rev -> {
            FinancialExpenseResponse response = toResponse(rev, FinancialExpenseResponse.class);

            responseList.add(response);
        });

        return new PageableBean<FinancialExpenseResponse>()
                .withPageable(pageable)
                .withContent(responseList)
                .withTotal(financialExpenses.getTotalElements())
                .getPaged();

    }
}
