package com.will.caleb.business.model.records.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.will.caleb.business.model.records.requests.AddressRequest;

import java.util.Date;

public record EnterpriseResponse(Integer id,
                                 String name,
                                 String email,
                                 String phone,
                                 String document,
                                 AddressRequest address,
                                 @JsonFormat(pattern = "yyyy-MM-dd")
                                 Date includeDate,
                                 @JsonFormat(pattern = "yyyy-MM-dd")
                                 Date updatedAt) implements AbstractResponseDTO {
}
