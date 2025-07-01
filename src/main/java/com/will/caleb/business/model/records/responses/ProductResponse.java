package com.will.caleb.business.model.records.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.will.caleb.business.model.enums.EnumStatusProduto;
import com.will.caleb.business.model.enums.EnumTipoProduto;

import java.math.BigDecimal;
import java.util.Date;

public record ProductResponse(
    Integer id,
    String codigo,
    String nsu,
    String nome,
    String descricao,
    String categoria,
    EnumTipoProduto tipo,
    BigDecimal precoCompra,
    BigDecimal precoVenda,
    BigDecimal margemLucro,
    ProdutoEstoqueResponse estoque,
    EnumStatusProduto status,
    String fornecedor,
    String unidadeMedida,
    BigDecimal peso,
    ProdutoDimensoesResponse dimensoes,
    String observacoes,
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date createdAt,
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date updatedAt
) implements AbstractResponseDTO {}
