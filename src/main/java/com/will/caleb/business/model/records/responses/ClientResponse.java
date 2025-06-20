package com.will.caleb.business.model.records.responses;

import com.will.caleb.business.model.dto.AbstractDTO;
import com.will.caleb.business.model.entity.Address;

import java.util.Date;

public record ClientResponse(Integer id,
                             String name,
                             Date birthdayDate,
                             String document,
                             Date inclusionDate,
                             String gender,
                             Date editDate,
                             AddressResponse address,
                             String email,
                             String phone) implements AbstractDTO {
}
