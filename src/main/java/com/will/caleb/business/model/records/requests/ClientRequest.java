package com.will.caleb.business.model.records.requests;

import java.util.Date;

public record ClientRequest(Integer id,
                            String name,
                            Date birthdayDate,
                            String document,
                            String gender,
                            String email,
                            String phone,
                            AddressRequest address) implements AbstractRequestDTO {
}
