package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.FinancialGoal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FinancialGoalService {

    FinancialGoal include(FinancialGoal financialGoal);

    FinancialGoal edit(FinancialGoal financialGoal);

    Page<FinancialGoal> list(Pageable pageable, Integer mes, Integer ano);

}
