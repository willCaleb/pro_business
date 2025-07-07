package com.will.caleb.business.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_produto_estoque")
public class ProdutoEstoque extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "minimo")
    private Integer minimo;

    @Column(name = "maximo")
    private Integer maximo;

}
