package com.will.caleb.business.service.impl;

import com.will.caleb.business.model.entity.Client;
import com.will.caleb.business.repository.ClientRepository;
import com.will.caleb.business.service.ClientService;
import com.will.caleb.business.validator.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends AbstractServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientValidator clientValidator;

    @Override
    public Client include(Client client) {
        clientValidator.validateInsert(client, getIdEnterpriseByContext());
        client.setInclusionDate(new Date());
        client.setEnterprise(getIdEnterpriseByContext());
        return clientRepository.save(client);
    }

    @Override
    public Client edit(Client client) {
        Client clienteManaged = clientRepository.findById(client.getId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        clienteManaged.setGender(client.getDocument());
        clienteManaged.setName(client.getName());
        clienteManaged.setBirthdayDate(client.getBirthdayDate());
        clienteManaged.setEditDate(new Date());
        clienteManaged.setAddress(client.getAddress());
        clienteManaged.setEmail(client.getEmail());
        clienteManaged.setPhone(client.getPhone());

        return clientRepository.save(clienteManaged);
    }

    @Override
    public Page<Client> listAll(Pageable pageable) {
        return clientRepository.findAllByEnterprise(getIdEnterpriseByContext(), pageable);
    }
}
