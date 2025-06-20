package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {

    Client include(Client client);

    void edit(Integer clientId, Client client);

    Page<Client> listAll(Pageable pageable);

}
