package com.will.caleb.business.model.records.requests;

import com.will.caleb.business.model.enums.EnumStatusProduto;
import com.will.caleb.business.model.enums.EnumTipoProduto;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

public record ProductRequest(Integer id,
                             String codigo,
                             String nsu,
                             String nome,
                             String descricao,
                             String categoria,
                             EnumTipoProduto tipo,
                             BigDecimal precoCompra,
                             BigDecimal precoVenda,
                             BigDecimal margemLucro,
                             ProdutoEstoqueRequest estoque,
                             EnumStatusProduto status,
                             String fornecedor,
                             String unidadeMedida,
                             BigDecimal peso,
                             ProdutoDimensoesRequest dimensoes,
                             String observacoes) implements AbstractRequestDTO{
}
