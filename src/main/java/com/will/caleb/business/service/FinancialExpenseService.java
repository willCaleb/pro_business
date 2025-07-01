package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.FinancialExpense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinancialExpenseService {

    FinancialExpense include(FinancialExpense expense);

    FinancialExpense edit(FinancialExpense expense);

    Page<FinancialExpense> list(Pageable pageable);

    Page<FinancialExpense> findAllFuture(Pageable pageable);
}
