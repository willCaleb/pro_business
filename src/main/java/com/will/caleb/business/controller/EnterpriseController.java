package com.will.caleb.business.controller;

import com.will.caleb.business.model.entity.Enterprise;
import com.will.caleb.business.model.records.requests.EnterpriseRequest;
import com.will.caleb.business.model.records.responses.EnterpriseResponse;
import com.will.caleb.business.service.EnterpriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(EnterpriseController.PATH)
public class EnterpriseController extends AbstractController{

    public static final String PATH = "${api.prefix.v1}/enterprise";

    private final EnterpriseService enterpriseService;

    @GetMapping
    EnterpriseResponse findByContext() {
         return toResponse(enterpriseService.getByContext(), EnterpriseResponse.class);
    }

    @PutMapping
    EnterpriseResponse edit(@RequestBody EnterpriseRequest enterpriseRequest) {

        Enterprise enterprise = toEntity(enterpriseRequest, Enterprise.class);

        return toResponse(enterpriseService.edit(enterprise), EnterpriseResponse.class);
    }

}
