package com.will.caleb.business.model.records.requests;

public record EnterpriseRequest(String name, String email, String phone, String document, AddressRequest address) implements AbstractRequestDTO{
}
