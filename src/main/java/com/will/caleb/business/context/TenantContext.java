package com.will.caleb.business.context;

import com.will.caleb.business.model.entity.Enterprise;

public class TenantContext {

    private static final ThreadLocal<Integer> currentEnterprise = new ThreadLocal<>();

    public static void setEnterprise(Enterprise enterprise) {
        currentEnterprise.set(enterprise.getId());
    }

    public static Integer getEnterprise() {
        try {
            return currentEnterprise.get();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void setEnterprise(Integer enterriseId) {
        clearContext();
        currentEnterprise.set(enterriseId);
    }

    public static void clearContext() {
        currentEnterprise.remove();
    }

}
