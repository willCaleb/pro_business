package com.will.caleb.business.service;

import com.will.caleb.business.model.entity.FinancialMovement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FinancialMovementService {

    FinancialMovement include(FinancialMovement movement);

    FinancialMovement edit(FinancialMovement movement);

    Page<FinancialMovement> listPastMovements(Pageable pageable);

    Page<FinancialMovement> listFutureMovements(Pageable pageable);

    void delete(Integer idMovement);

}
