package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.Enterprise;

public interface EnterpriseService {

    Enterprise include(Enterprise enterpriseRequest);

    Enterprise getByContext();

    Enterprise findById(Integer id);

    Enterprise edit(Enterprise enterprise);
}
