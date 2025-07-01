package com.will.caleb.business.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_financial_movement")
public class FinancialMovement extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo")
    private String tipo; //receita / despesa TODO trocar por enum

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_vencimento")
    private Date dataVencimento;

    private String status; // pendente / confirmado TODO trocar por enum

//    @ManyToOne(fetch = FetchType.EAGER)
    private String client; //TODO trocar por entidade

    private String fornecedor; //TODO criar entidade fornecedor

}
