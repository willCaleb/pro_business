package com.will.caleb.business.model.records.requests;

import com.will.caleb.business.model.dto.AbstractDTO;
import com.will.caleb.business.model.entity.Address;

import java.util.Date;

public record ClientRequest(String name,
                            Date birthdayDate,
                            String document,
                            String gender,
                            String email,
                            String phone,
                            AddressRequest address) implements AbstractDTO {
}
