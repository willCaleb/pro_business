package com.will.caleb.business.repository;

import com.will.caleb.business.model.entity.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Integer>, JpaSpecificationExecutor<FinancialMovement> {
}
