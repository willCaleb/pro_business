package com.will.caleb.business.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_financial_expense")
public class FinancialExpense extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data")
    private Date data;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "fornecedor_relacionado")
    private String fornecedorRelacionado; //TODO criar fornecedor e alterar para relacionamento

    @Column(name = "recorrente")
    private boolean recorrente;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
