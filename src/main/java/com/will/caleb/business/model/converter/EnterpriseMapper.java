package com.will.caleb.business.model.converter;

import com.will.caleb.business.model.entity.Enterprise;
import com.will.caleb.business.model.records.requests.EnterpriseRequest;
import com.will.caleb.business.model.records.responses.EnterpriseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {

    Enterprise toEntity(EnterpriseRequest request);

    EnterpriseResponse toResponse(Enterprise enterprise);




}
