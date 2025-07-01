package com.will.caleb.business.service.impl;

import com.will.caleb.business.model.records.beans.WebhookPayload;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebhookService {

    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:5678")
            .build();

    public void enviarParaWebhook(Object object) {

        webClient.post()
                .uri("/webhook-test/business")
                .bodyValue(object)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(v -> System.out.println("Webhook enviado com sucesso!"))
                .doOnError(e -> System.err.println("Erro ao enviar webhook: " + e.getMessage()))
                .subscribe();
    }
}
