package com.will.caleb.business.controller.financial;

import com.will.caleb.business.model.entity.FinancialRevenue;
import com.will.caleb.business.model.records.requests.FinancialRevenueRequest;
import com.will.caleb.business.model.records.responses.FinancialRevenueResponse;
import com.will.caleb.business.pattern.PageableBean;
import com.will.caleb.business.service.FinancialRevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(FinancialRevenueController.PATH)
public class FinancialRevenueController extends AbstractFinancialController {
    public static final String PATH = FINANCIAL_PATH + "/revenue";

    private final FinancialRevenueService financialRevenueService;

    @GetMapping("/list")
    public Page<FinancialRevenueResponse> findAll(Pageable pageable){

        List<FinancialRevenueResponse> responseList = new ArrayList<>();

        Page<FinancialRevenue> financialRevenues = financialRevenueService.list(pageable);

        financialRevenues.forEach(rev -> {
            FinancialRevenueResponse response = toResponse(rev, FinancialRevenueResponse.class);

            responseList.add(response);
        });

        return new PageableBean<FinancialRevenueResponse>()
                .withPageable(pageable)
                .withContent(responseList)
                .withTotal(financialRevenues.getTotalElements())
                .getPaged();
    }

    @PostMapping("/include")
    public FinancialRevenueResponse include(@RequestBody FinancialRevenueRequest financialRevenueRequest) {
        FinancialRevenue financialRevenue = toEntity(financialRevenueRequest, FinancialRevenue.class);

        return toResponse(financialRevenueService.include(financialRevenue), FinancialRevenueResponse.class);
    }

    @PutMapping("/edit")
    public FinancialRevenueResponse edit(@RequestBody FinancialRevenueRequest financialRevenueRequest) {
        FinancialRevenue financialRevenue = toEntity(financialRevenueRequest, FinancialRevenue.class);

        return toResponse(financialRevenueService.edit(financialRevenue), FinancialRevenueResponse.class);
    }
}
