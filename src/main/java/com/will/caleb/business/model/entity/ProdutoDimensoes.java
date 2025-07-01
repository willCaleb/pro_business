package com.will.caleb.business.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto_dimensoes")
public class ProdutoDimensoes extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "altura")
    private BigDecimal altura;

    @Column(name = "largura")
    private BigDecimal largura;

    @Column(name = "profundidade")
    private BigDecimal profundidade;

}
