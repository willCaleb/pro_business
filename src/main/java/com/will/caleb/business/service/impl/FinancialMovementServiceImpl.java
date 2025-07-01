package com.will.caleb.business.service.impl;


import com.will.caleb.business.model.entity.FinancialMovement;
import com.will.caleb.business.service.FinancialMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancialMovementServiceImpl extends AbstractServiceImpl implements FinancialMovementService {

    @Override
    public FinancialMovement include(FinancialMovement movement) {
        return null;
    }

    @Override
    public FinancialMovement edit(FinancialMovement movement) {
        return null;
    }

    @Override
    public Page<FinancialMovement> listPastMovements(Pageable pageable) {
        return null;
    }

    @Override
    public void delete(Integer idMovement) {

    }

    @Override
    public Page<FinancialMovement> listFutureMovements(Pageable pageable) {
        return null;
    }
}
