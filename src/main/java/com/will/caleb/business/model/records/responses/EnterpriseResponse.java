package com.will.caleb.business.model.records.responses;

import com.will.caleb.business.model.records.requests.AddressRequest;

public record EnterpriseResponse(String name, String email, String phone, String document, AddressRequest address) implements AbstractResponseDTO {
}
