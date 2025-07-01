package com.will.caleb.business.repository;

import com.will.caleb.business.model.entity.FinancialExpense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface FinancialExpenseRepository extends JpaRepository<FinancialExpense, Integer>, JpaSpecificationExecutor<FinancialExpense> {

    Page<FinancialExpense> findAllByEnterprise(Integer enterprise, Pageable pageable);

    @Query("""
            select exp from FinancialExpense exp
             where data > :now
               and enterprise = :enterprise
            """)
    Page<FinancialExpense> findAllFuture(Integer enterprise, LocalDateTime now, Pageable pageable);
}
