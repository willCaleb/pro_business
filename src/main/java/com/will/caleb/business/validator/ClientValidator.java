package com.will.caleb.business.validator;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.EnumCustomException;
import com.will.caleb.business.model.entity.Client;
import com.will.caleb.business.repository.ClientRepository;
import com.will.caleb.business.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientValidator extends AbstractValidator{

    private final ClientRepository clientRepository;

    public void validateInsert(Client client, Integer enterprise) {
        validateRequiredFields(client);
        validateDuplicatedDocument(client, enterprise);
        validateDuplicatedEmail(client, enterprise);
    }

    private void validateRequiredFields(Client client) {
        addFieldToValidate("Nome", client.getName());
        addFieldToValidate("Telefone", client.getPhone());
        addFieldToValidate("E-mail", client.getEmail());
        addFieldToValidate("Endereço", client.getAddress());
        validate();
    }

    private void validateDuplicatedEmail(Client client, Integer enterprise) {
        Client byEmail = clientRepository.findByEmailAndEnterprise(client.getEmail(), enterprise).orElse(null);

        if (Utils.isNotEmpty(byEmail)) {
            throw new CustomException(EnumCustomException.CLIENT_DUPLICATED_EMAIL, client.getEmail(), byEmail.getName());
        }
    }

    private void validateDuplicatedDocument(Client client, Integer enterprise) {
        Optional<Client> byDocument = clientRepository.findByDocumentAndEnterprise(client.getDocument(), enterprise);

        if (Utils.isNotEmpty(byDocument)) {
            throw new CustomException(EnumCustomException.CLIENT_DUPLICATED_DOCUMENT, client.getDocument(), client.getName());
        }
    }
}
