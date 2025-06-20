package com.will.caleb.business.model.records.converters;

import com.will.caleb.business.model.entity.Client;
import com.will.caleb.business.model.records.requests.ClientRequest;
import com.will.caleb.business.model.records.responses.ClientResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientResponse toResponse(Client client);
    Client toEntity(ClientRequest request);
    ClientRequest toRequest(Client client);
}