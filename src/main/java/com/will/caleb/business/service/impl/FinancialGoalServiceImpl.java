package com.will.caleb.business.service.impl;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import com.will.caleb.business.model.entity.FinancialGoal;
import com.will.caleb.business.repository.FinancialGoalRepository;
import com.will.caleb.business.service.FinancialGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class FinancialGoalServiceImpl extends AbstractServiceImpl implements FinancialGoalService {

    private final FinancialGoalRepository financialGoalRepository;

    @Override
    public FinancialGoal include(FinancialGoal financialGoal) {

        financialGoal.setCreatedAt(new Date());

        return financialGoalRepository.save(financialGoal);
    }

    @Override
    public FinancialGoal edit(FinancialGoal financialGoal) {

        FinancialGoal financialGoalManaged = financialGoalRepository
                .findById(financialGoal.getId())
                .orElseThrow(() -> new CustomException(EnumCustomException.FINANCIAL_GOAL_NOT_FOUND));

        financialGoalManaged.setMes(financialGoal.getMes());
        financialGoalManaged.setAno(financialGoal.getAno());
        financialGoalManaged.setUpdatedAt(new Date());
        financialGoalManaged.setMetaDespesaMensal(financialGoal.getMetaDespesaMensal());
        financialGoalManaged.setEnterprise(getIdEnterpriseByContext());
        return financialGoalRepository.save(financialGoalManaged);
    }

    @Override
    public Page<FinancialGoal> list(Pageable pageable, Integer mes, Integer ano) {
        return financialGoalRepository.findAllByEnterpriseAndMesAndAno(getIdEnterpriseByContext(), mes, ano, pageable);
    }
}
