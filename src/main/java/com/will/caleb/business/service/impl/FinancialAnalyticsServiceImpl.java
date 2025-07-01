package com.will.caleb.business.service.impl;

import com.will.caleb.business.model.classes.DashboardFinanceiroResponse;
import com.will.caleb.business.repository.custom.CustomFinancialAnalyticsRepository;
import com.will.caleb.business.service.FinancialAnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class FinancialAnalyticsServiceImpl extends AbstractServiceImpl implements FinancialAnalyticsService {

    private final CustomFinancialAnalyticsRepository customFinancialAnalyticsRepository;

    @Override
    public DashboardFinanceiroResponse findAnalytics(Date initialDate, Date finalDate) {
        DashboardFinanceiroResponse dashboardFinanceiroResponse = customFinancialAnalyticsRepository.buscarDashboard(initialDate, finalDate, getIdEnterpriseByContext());

        return dashboardFinanceiroResponse;

    }
}
