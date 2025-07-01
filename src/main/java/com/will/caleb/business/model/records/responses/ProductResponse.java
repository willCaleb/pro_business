package com.will.caleb.business.model.records.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
    Long id,
    String codigo,
    String nsu,
    String nome,
    String descricao,
    String categoria,
    Tipo tipo,
    BigDecimal precoCompra,
    BigDecimal precoVenda,
    BigDecimal margemLucro,
    EstoqueResponse estoque,
    Status status,
    String fornecedor,
    String unidadeMedida,
    BigDecimal peso,
    DimensoesResponse dimensoes,
    String observacoes,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
