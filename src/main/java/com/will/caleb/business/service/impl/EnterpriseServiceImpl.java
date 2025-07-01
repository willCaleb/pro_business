package com.will.caleb.business.service.impl;

import com.will.caleb.business.context.TenantContext;
import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import com.will.caleb.business.model.entity.Enterprise;
import com.will.caleb.business.repository.EnterpriseRepository;
import com.will.caleb.business.service.EnterpriseService;
import com.will.caleb.business.validator.EnterpriseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.will.caleb.business.utils.Utils;

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
        return enterpriseRepository
                .findById(TenantContext.getEnterprise())
                .orElseThrow(() -> new CustomException(EnumCustomException.ENTERPRISE_ERROR_DATA_NOT_FOUND));
    }

    @Override
    public Enterprise findById(Integer id) {
        return enterpriseRepository.findById(id).orElseThrow();
    }

    @Override
    public Enterprise edit(Enterprise enterprise) {

        Enterprise enterpriseManaged = enterpriseRepository
                .findById(TenantContext.getEnterprise())
                .orElseThrow(() -> new CustomException(EnumCustomException.ENTERPRISE_ERROR_DATA_NOT_FOUND));

        enterpriseManaged.setName(Utils.nvl(enterprise.getName(), enterpriseManaged.getName()));
        enterpriseManaged.setAddress(Utils.nvl(enterprise.getAddress(), enterpriseManaged.getAddress()));
        enterpriseManaged.setPhone(Utils.nvl(enterprise.getPhone(), enterpriseManaged.getPhone()));
        enterpriseManaged.setDocument(Utils.nvl(enterprise.getDocument(), enterpriseManaged.getDocument()));
        enterpriseManaged.setEmail(Utils.nvl(enterprise.getEmail(), enterpriseManaged.getEmail()));
        enterpriseManaged.setPhone(Utils.nvl(enterprise.getPhone(), enterpriseManaged.getPhone()));
        enterpriseManaged.setUpdateAt(new Date());

        enterpriseValidator.validateUpdate(enterpriseManaged);

        validateDuplicatedEmailOrCnpj(enterprise, enterpriseManaged);

        return enterpriseRepository.save(enterpriseManaged);
    }

    private void validateDuplicatedEmailOrCnpj(Enterprise enterprise, Enterprise enterpriseManaged) {
        if (!Utils.equals(enterprise.getDocument(), enterpriseManaged.getDocument())) {
            enterpriseValidator.validateDuplicatedCnpj(enterprise);
        }
        if (!Utils.equals(enterprise.getEmail(), enterpriseManaged.getEmail())) {
            enterpriseValidator.validateDuplicatedEmail(enterprise);
        }
    }
}
