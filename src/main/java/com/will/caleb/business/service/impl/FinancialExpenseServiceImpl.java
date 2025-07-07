package com.will.caleb.business.service.impl;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import com.will.caleb.business.model.entity.FinancialExpense;
import com.will.caleb.business.repository.FinancialExpenseRepository;
import com.will.caleb.business.service.FinancialExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class FinancialExpenseServiceImpl extends AbstractServiceImpl implements FinancialExpenseService {

    private final FinancialExpenseRepository financialExpenseRepository;

    @Override
    public FinancialExpense include(FinancialExpense expense) {

        expense.setCreatedAt(new Date());
        return financialExpenseRepository.save(expense);
    }

    @Override
    public FinancialExpense edit(FinancialExpense expense) {

        FinancialExpense expenseManaged = financialExpenseRepository
                .findById(expense.getId())
                .orElseThrow(() -> new CustomException(EnumCustomException.FINANCIAL_EXPENSE_NOT_FOUND));

        expenseManaged.setData(expense.getData());
        expenseManaged.setCategoria(expense.getCategoria());
        expenseManaged.setDescricao(expense.getDescricao());
        expenseManaged.setRecorrente(expense.isRecorrente());
        expenseManaged.setFornecedorRelacionado(expense.getFornecedorRelacionado());
        expenseManaged.setUpdatedAt(new Date());
        expenseManaged.setValor(expense.getValor());

        return financialExpenseRepository.save(expenseManaged);
    }

    @Override
    public Page<FinancialExpense> list(Pageable pageable) {
        return financialExpenseRepository.findAllByEnterprise(getIdEnterpriseByContext(), pageable);
    }

    @Override
    public Page<FinancialExpense> findAllFuture(Pageable pageable) {
        return financialExpenseRepository.findAllFuture(getIdEnterpriseByContext(), LocalDateTime.now(), pageable);
    }
}
