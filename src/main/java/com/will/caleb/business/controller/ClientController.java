package com.will.caleb.business.controller;

import com.will.caleb.business.model.entity.Client;
import com.will.caleb.business.model.records.converters.ClientMapper;
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
    private final ClientMapper clientMapper;

    public static final String PATH = "${api.prefix.v1}/client";


    @PostMapping("/include")
    public ClientResponse include(@RequestBody ClientRequest clientRequest) {
        Client client = clientMapper.toEntity(clientRequest);

        Client include = clientService.include(client);

        return clientMapper.toResponse(include);
    }

    @GetMapping("/list")
    Page<ClientResponse> findAll(Pageable pageable) {
        Page<Client> clients = clientService.listAll(pageable);

        return clients.map(clientMapper::toResponse
        );
    }

}
