package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.FinancialRevenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinancialRevenueService {

    FinancialRevenue include(FinancialRevenue financialRevenue);

    FinancialRevenue edit(FinancialRevenue financialRevenue);

    Page<FinancialRevenue> list(Pageable pageable);

    Page<FinancialRevenue> findAllFuture(Pageable pageable);
}
