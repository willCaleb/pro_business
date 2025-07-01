package com.will.caleb.business.model.records.responses;

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
                             String phone) implements AbstractResponseDTO {
}
