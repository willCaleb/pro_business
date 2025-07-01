package com.will.caleb.business.repository;

import com.will.caleb.business.model.entity.FinancialRevenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface FinancialRevenueRepository extends JpaRepository<FinancialRevenue, Integer>, JpaSpecificationExecutor<FinancialRevenue> {
    Page<FinancialRevenue> findAllByEnterprise(Integer idEnterpriseByContext, Pageable pageable);

    @Query(value = """
            select rev from FinancialRevenue rev
             where data > :date
               and enterprise = :enterpriseId
            """)
    Page<FinancialRevenue> findAllFuture(Integer enterpriseId, LocalDateTime date, Pageable pageable);
}
