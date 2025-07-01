package com.will.caleb.business.controller.financial;

import com.will.caleb.business.controller.AbstractController;
import com.will.caleb.business.model.entity.FinancialMovement;
import com.will.caleb.business.model.records.requests.FinancialMovementRequest;
import com.will.caleb.business.model.records.responses.FinancialMovementResponse;
import com.will.caleb.business.service.FinancialMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(FinancialMovementController.PATH)
public class FinancialMovementController extends AbstractFinancialController {

    public static final String PATH = FINANCIAL_PATH + "/future/revenues";

    private final FinancialMovementService financialMovementService;

    @PostMapping("/include")
    public FinancialMovementResponse include(@RequestBody FinancialMovementRequest request) {
        FinancialMovement financialMovement = toEntity(request, FinancialMovement.class);

        return toResponse(financialMovementService.include(financialMovement), FinancialMovementResponse.class);
    }

}
