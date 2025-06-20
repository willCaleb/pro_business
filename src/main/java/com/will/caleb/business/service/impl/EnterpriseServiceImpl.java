package com.will.caleb.business.service.impl;

import com.will.caleb.business.context.TenantContext;
import com.will.caleb.business.model.entity.Enterprise;
import com.will.caleb.business.repository.EnterpriseRepository;
import com.will.caleb.business.service.EnterpriseService;
import com.will.caleb.business.validator.EnterpriseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    private final EnterpriseValidator enterpriseValidator;

    @Override
    public Enterprise include(Enterprise enterprise) {

        enterpriseValidator.validateInsert(enterprise);

        enterprise.setIncludeDate(new Date());

        return enterpriseRepository.save(enterprise);
    }

    @Override
    public Enterprise getByContext() {
        Integer id = TenantContext.getEnterprise();
        return enterpriseRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro ao buscar empresa"));
    }

    @Override
    public Enterprise findById(Integer id) {
        return enterpriseRepository.findById(id).orElseThrow();
    }
}
