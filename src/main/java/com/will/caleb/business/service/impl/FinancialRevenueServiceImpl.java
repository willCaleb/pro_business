package com.will.caleb.business.service.impl;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import com.will.caleb.business.model.entity.FinancialRevenue;
import com.will.caleb.business.repository.FinancialRevenueRepository;
import com.will.caleb.business.service.FinancialRevenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialRevenueServiceImpl extends AbstractServiceImpl implements FinancialRevenueService {

    private final FinancialRevenueRepository financialRevenueRepository;

    @Override
    public FinancialRevenue include(FinancialRevenue financialRevenue) {
        financialRevenue.setEnterprise(getIdEnterpriseByContext());
        financialRevenue.setCreatedAt(new Date());
        return financialRevenueRepository.save(financialRevenue);
    }

    @Override
    public FinancialRevenue edit(FinancialRevenue financialRevenue) {

        FinancialRevenue financialRevenueManaged = financialRevenueRepository
                .findById(financialRevenue.getId())
                .orElseThrow(() -> new CustomException(EnumCustomException.FINANCIAL_REVENUE_NOT_FOUND));

        financialRevenueManaged.setCategoria(financialRevenue.getCategoria());
        financialRevenueManaged.setData(financialRevenue.getData());
        financialRevenueManaged.setDescricao(financialRevenue.getDescricao());
        financialRevenueManaged.setValor(financialRevenue.getValor());
        financialRevenueManaged.setUpdatedAt(new Date());
        financialRevenueManaged.setClienteRelacionado(financialRevenue.getClienteRelacionado());

        return financialRevenueRepository.save(financialRevenueManaged);
    }

    @Override
    public Page<FinancialRevenue> list(Pageable pageable) {
        return financialRevenueRepository.findAllByEnterprise(getIdEnterpriseByContext(), pageable);
    }

    @Override
    public Page<FinancialRevenue> findAllFuture(Pageable pageable) {
        return financialRevenueRepository.findAllFuture(getIdEnterpriseByContext(), LocalDateTime.now(), pageable);
    }
}
