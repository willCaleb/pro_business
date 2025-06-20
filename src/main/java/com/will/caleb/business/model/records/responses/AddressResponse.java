package com.will.caleb.business.model.records.responses;

import com.will.caleb.business.model.dto.AbstractDTO;

public record AddressResponse(Integer id,
                              String zipcode,
                              String streetName,
                              Integer number,
                              String city,
                              String state,
                              String complement) implements AbstractDTO {
}
