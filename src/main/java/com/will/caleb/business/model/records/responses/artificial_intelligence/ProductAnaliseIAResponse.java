package com.will.caleb.business.model.records.responses.artificial_intelligence;

import java.util.List;

public record ProductAnaliseIAResponse(

    RespostaIA respostaIA
) {
    public record RespostaIA(
        String situacao,
        List<String> pontosFortes,
        List<String> pontosFracos,
        List<String> riscos,
        List<String> oportunidades,
        List<String> acoesEstrategicas,
        List<String> sugestoesNovasVendas,
        List<String> estrategiasFidelizacao,
        List<String> melhoriaVendas,
        List<String> produtosCriticos,
        List<String>produtosSugeridosParaRemocao
    ) implements AbstractArtificialIntelligenceResponse{}
}