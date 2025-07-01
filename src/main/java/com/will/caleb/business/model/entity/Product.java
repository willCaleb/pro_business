package com.will.caleb.business.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

    private Long id;
    private String codigo;
    private String nsu;
    private String nome;
    private String descricao;
    private String categoria;
    private Tipo tipo; // ENUM: PRODUTO, SERVICO
    private BigDecimal precoCompra;
    private BigDecimal precoVenda;
    private BigDecimal margemLucro;
    private Estoque estoque;
    private Status status; // ENUM: ATIVO, INATIVO, DESCONTINUADO
    private String fornecedor;
    private String unidadeMedida;
    private BigDecimal peso;
    private Dimensoes dimensoes;
    private String observacoes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters e Setters (gerar via Lombok ou IDE)
}
