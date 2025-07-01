package com.will.caleb.business.repository;

import com.will.caleb.business.model.entity.FinancialGoal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, Integer>, JpaSpecificationExecutor<FinancialGoal> {
    Page<FinancialGoal> findAllByEnterpriseAndMesAndAno(Integer idEnterpriseByContext, Integer mes, Integer ano, Pageable pageable);
}
