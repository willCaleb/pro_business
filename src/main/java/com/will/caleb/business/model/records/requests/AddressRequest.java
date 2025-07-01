package com.will.caleb.business.model.records.requests;

public record AddressRequest ( Integer id,
                               String zipcode,
                               String streetName,
                               Integer number,
                               String city,
                               String state,
                               String complement) implements AbstractRequestDTO {
}
