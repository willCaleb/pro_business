package com.will.caleb.business.repository;

import com.will.caleb.business.model.entity.ProdutoDimensoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoDimensoesRepository extends JpaRepository<ProdutoDimensoes, Integer>, JpaSpecificationExecutor<ProdutoDimensoes> {
}
