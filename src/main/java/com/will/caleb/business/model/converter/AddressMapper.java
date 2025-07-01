package com.will.caleb.business.model.converter;

import com.will.caleb.business.model.entity.Address;
import com.will.caleb.business.model.records.requests.AddressRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressRequest request);

}
