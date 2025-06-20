package com.will.caleb.business.validator;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import com.will.caleb.business.model.entity.Enterprise;
import com.will.caleb.business.repository.EnterpriseRepository;
import com.will.caleb.business.utils.ListUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EnterpriseValidator extends AbstractValidator{

    private final EnterpriseRepository enterpriseRepository;

    public void validateInsert(Enterprise enterprise) {
        validateMandatoryFields(enterprise);
        validateDuplicatedCnpj(enterprise);
        validateDuplicatedEmail(enterprise);
    }

    private void validateDuplicatedEmail(Enterprise enterprise) {
        List<Enterprise> allByEmail = enterpriseRepository.findAllByEmail(enterprise.getEmail());

        if (ListUtil.isNotEmpty(allByEmail)) {
            throw new CustomException(EnumCustomException.ENTERPRISE_DUPLICATED_EMAIL);
        }
    }

    private void validateDuplicatedCnpj(Enterprise enterprise) {
        List<Enterprise> allByCnpj = enterpriseRepository.findAllByCnpj(enterprise.getCnpj());

        if (ListUtil.isNotEmpty(allByCnpj)) {
            throw new CustomException(EnumCustomException.ENTERPRISE_DUPLICATED_CNPJ);
        }
    }

    private void validateMandatoryFields(Enterprise enterprise) {
        addFieldToValidate("Nome", enterprise.getName());
        addFieldToValidate("E-mail", enterprise.getEmail());
        addFieldToValidate("CNPJ", enterprise.getCnpj());
        addFieldToValidate("Telefone", enterprise.getPhone());
        validate();
    }
}
