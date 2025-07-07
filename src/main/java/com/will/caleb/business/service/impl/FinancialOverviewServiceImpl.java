package com.will.caleb.business.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.caleb.business.model.records.responses.artificial_intelligence.FinancialOverviewResponse;
import com.will.caleb.business.pattern.constants.Constants;
import com.will.caleb.business.repository.custom.CustomFinancialOverviewRepository;
import com.will.caleb.business.service.FinancialOverviewService;
import com.will.caleb.business.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FinancialOverviewServiceImpl extends AbstractServiceImpl implements FinancialOverviewService {

    private final CustomFinancialOverviewRepository financialOverviewRepository;

    private final ObjectMapper objectMapper;

    public FinancialOverviewResponse generateFinancialOverview() {

        FinancialOverviewResponse original = financialOverviewRepository.getFinancialOvewview(getIdEnterpriseByContext());

        boolean hasMovement = Utils.isNotEmpty(original);

        String endpointUrl = hasMovement ? "/financial-analytics" : "/financial-advice";

        final String finalUrl = Constants.AI_WEBHOOK_URL + endpointUrl;

        try {
            if (hasMovement) {
                System.out.println(original);
                return sendToAiWebhookWithData(original, finalUrl);
            }
            return sendToAiWebhookWithoutData(finalUrl);

        } catch (Exception e) {
            e.printStackTrace();
            return original;
        }
    }

    private FinancialOverviewResponse sendToAiWebhookWithoutData(String finalUrl) {
        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    finalUrl,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return  objectMapper.readValue(response.getBody(), FinancialOverviewResponse.class);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private FinancialOverviewResponse sendToAiWebhookWithData(FinancialOverviewResponse original, String finalUrl) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        String json = objectMapper.writeValueAsString(original);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                finalUrl,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return objectMapper.readValue(response.getBody(), FinancialOverviewResponse.class);
        } else {
            return original;
        }
    }
}
