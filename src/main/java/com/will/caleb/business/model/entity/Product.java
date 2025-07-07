package com.will.caleb.business.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.will.caleb.business.model.enums.EnumStatusProduto;
import com.will.caleb.business.model.enums.EnumTipoProduto;
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
@Table(name = "bus_product")
public class Product extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "nsu")
    private String nsu;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "tipo")
    private EnumTipoProduto tipo;

    @Column(name = "preco_compra")
    private BigDecimal precoCompra;

    @Column(name = "preco_venda")
    private BigDecimal precoVenda;

    @Column(name = "margem_lucro")
    private BigDecimal margemLucro;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produto_estoque", referencedColumnName = "id")
    private ProdutoEstoque estoque;

    @Column(name = "status")
    private EnumStatusProduto status;

    @Column(name = "fornecedor")
    private String fornecedor;

    @Column(name = "unidade_medida")
    private String unidadeMedida;

    @Column(name = "peso")
    private BigDecimal peso;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_produto_dimensoes", referencedColumnName = "id")
    private ProdutoDimensoes dimensoes;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

}
