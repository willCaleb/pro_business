package com.will.caleb.business.auth.tenant;

import com.will.caleb.business.context.TenantContext;
import com.will.caleb.business.utils.Utils;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<Integer>, HibernatePropertiesCustomizer {

    @Override
    public Integer resolveCurrentTenantIdentifier() {
        if (Utils.isNotEmpty(TenantContext.getEnterprise())) return TenantContext.getEnterprise();

        return 0;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }

}