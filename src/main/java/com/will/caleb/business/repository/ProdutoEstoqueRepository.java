package com.will.caleb.business.repository;

import com.will.caleb.business.model.entity.ProdutoDimensoes;
import com.will.caleb.business.model.entity.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Integer>, JpaSpecificationExecutor<ProdutoDimensoes> {
}
