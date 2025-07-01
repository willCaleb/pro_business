package com.will.caleb.business.controller;

import com.will.caleb.business.model.entity.Client;
import com.will.caleb.business.model.records.requests.ClientRequest;
import com.will.caleb.business.model.records.responses.ClientResponse;
import com.will.caleb.business.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ClientController.PATH)
public class ClientController extends AbstractController{

    private final ClientService clientService;

    public static final String PATH = "${api.prefix.v1}/client";


    @PostMapping("/include")
    public ClientResponse include(@RequestBody ClientRequest clientRequest) {
        Client client = toEntity(clientRequest, Client.class);

        Client include = clientService.include(client);

        return toResponse(include, ClientResponse.class);
    }

    @GetMapping("/list")
    Page<ClientResponse> findAll(Pageable pageable) {
        Page<Client> clients = clientService.listAll(pageable);

        return clients.map(client -> toResponse(client, ClientResponse.class));
    }

    @PutMapping("/edit")
    ClientResponse edit(@RequestBody ClientRequest clientRequest) {
        Client client = toEntity(clientRequest, Client.class);
        return toResponse(clientService.edit(client), ClientResponse.class);
    }

}
