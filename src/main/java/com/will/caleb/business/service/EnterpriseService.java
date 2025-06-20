package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.Enterprise;
import com.will.caleb.business.model.records.requests.EnterpriseRequest;

public interface EnterpriseService {

    Enterprise include(Enterprise enterpriseRequest);

    Enterprise getByContext();

    Enterprise findById(Integer id);
}
