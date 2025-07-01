package com.will.caleb.business.model.records.requests;

public record RegisterRequest(String username, String password, EnterpriseRequest enterprise) implements AbstractRequestDTO{
}
