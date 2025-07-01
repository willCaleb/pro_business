package com.will.caleb.business.controller.financial;

import com.will.caleb.business.model.entity.FinancialGoal;
import com.will.caleb.business.model.records.requests.FinancialGoalRequest;
import com.will.caleb.business.model.records.responses.FinancialGoalResponse;
import com.will.caleb.business.pattern.PageableBean;
import com.will.caleb.business.service.FinancialGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(FinancialGoalController.PATH)
public class FinancialGoalController extends AbstractFinancialController {

    private final FinancialGoalService financialGoalService;

    public static final String PATH = FINANCIAL_PATH + "/goals";

    @PostMapping(value = "/include")
    public FinancialGoalResponse include(@RequestBody FinancialGoalRequest request) {
        FinancialGoal financialGoal = toEntity(request, FinancialGoal.class);

        return toResponse(financialGoalService.include(financialGoal), FinancialGoalResponse.class);
    }

    @PutMapping("/edit")
    public FinancialGoalResponse edit(@RequestBody FinancialGoalRequest request) {
        FinancialGoal financialGoal = toEntity(request, FinancialGoal.class);

        return toResponse(financialGoalService.edit(financialGoal), FinancialGoalResponse.class );
    }

    @GetMapping("/list")
    Page<FinancialGoalResponse> list(Pageable pageable, @RequestParam("ano") Integer ano, @RequestParam("mes") Integer mes) {
        Page<FinancialGoal> financialGoals = financialGoalService.list(pageable, mes, ano);

        List<FinancialGoalResponse> responses = new ArrayList<>();

        financialGoals.forEach(goal -> {
            responses.add(toResponse(goal, FinancialGoalResponse.class));
        });

        return new PageableBean<FinancialGoalResponse>()
                .withPageable(pageable)
                .withContent(responses)
                .withTotal(financialGoals.getTotalElements())
                .getPaged();
    }
}
