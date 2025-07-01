package com.will.caleb.business.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.caleb.business.model.records.responses.FinancialOverviewResponse;
import com.will.caleb.business.repository.custom.CustomFinancialOverviewRepository;
import com.will.caleb.business.service.FinancialOverviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FinancialOverviewServiceImpl extends AbstractServiceImpl implements FinancialOverviewService {

    private final CustomFinancialOverviewRepository financialOverviewRepository;

    public FinancialOverviewResponse generateFinancialOverview() {
        FinancialOverviewResponse original = financialOverviewRepository.getFinancialOvewview(getIdEnterpriseByContext());

        try {
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper objectMapper = new ObjectMapper();

            String json = objectMapper.writeValueAsString(original);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://localhost:8000/webhook",
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return objectMapper.readValue(response.getBody(), FinancialOverviewResponse.class);
            } else {
                return original;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return original;
        }
    }
}
