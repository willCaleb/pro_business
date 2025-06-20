package com.will.caleb.business.service.impl;

import com.will.caleb.business.context.TenantContext;
import com.will.caleb.business.model.entity.Enterprise;

public abstract class AbstractServiceImpl {

    protected Integer getIdEnterpriseByContext() {
        return TenantContext.getEnterprise();
    }

}
