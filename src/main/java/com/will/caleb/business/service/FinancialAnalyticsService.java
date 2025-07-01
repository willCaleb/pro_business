package com.will.caleb.business.service;

import com.will.caleb.business.model.classes.DashboardFinanceiroResponse;

import java.util.Date;

public interface FinancialAnalyticsService {

    DashboardFinanceiroResponse findAnalytics(Date initialDate, Date finalDate);

}
