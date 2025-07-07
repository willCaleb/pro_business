package com.will.caleb.business.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.caleb.business.model.entity.Product;
import com.will.caleb.business.model.records.responses.artificial_intelligence.ProductAnaliseIAResponse;
import com.will.caleb.business.repository.ProductRepository;
import com.will.caleb.business.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends AbstractServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    @Override
    public Product incluir(Product product) {

        product.setCreatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product edit(Product product) {
        return null;
    }

    @Override
    public Page<Product> list(Pageable pageable) {
        return productRepository.findAllByEnterprise(getIdEnterpriseByContext(), pageable);
    }


    @Override
    public ProductAnaliseIAResponse generateAiAnalytics(Pageable pageable){

        List<Product> list = productRepository.findAllByEnterprise(getIdEnterpriseByContext(), pageable).stream().toList();

        Map<String, Object> wrapper = new HashMap<>();

        wrapper.put("produtos", list);

        try {
            RestTemplate restTemplate = new RestTemplate();

            String json = mapper.writeValueAsString(wrapper);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://localhost:8000/product-analytics",
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return mapper.readValue(response.getBody(), ProductAnaliseIAResponse.class);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();

            return new ProductAnaliseIAResponse(new ProductAnaliseIAResponse.RespostaIA("A análise do portfólio de produtos é impossibilitada devido à" +
                    " ausência de dados. Sem informações sobre os produtos (nome, categoria, tipo, preço de compra e venda, " +
                    "margem de lucro, status, estoque, peso, fornecedor), não é possível avaliar a situação geral da empresa, " +
                    "seus pontos fortes e fracos, riscos e oportunidades, nem propor ações estratégicas, sugestões de vendas, " +
                    "estratégias de fidelização, melhorias para vendas, identificar produtos críticos ou calcular indicadores.",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null));
        }
    }
}
