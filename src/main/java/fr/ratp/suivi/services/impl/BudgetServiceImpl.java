package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.Budget;
import fr.ratp.suivi.repositories.BudgetRepository;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.services.BudgetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BudgetServiceImpl implements BudgetService {

    @Autowired
    private final BudgetRepository budgetRepository;

    @Autowired
    private final LocalUnitRepository localUnitRepository;

    @Override
    public List<Budget> getAllBudgetByUnitCode(String localUnitCode) {

        return budgetRepository.getAllBudgetByUnitCode(localUnitCode);
    }

    @Override
    public List<Budget> getAllBudgetByYear(String year) {

        return budgetRepository.getAllBudgetByYear(year);
    }

    @Override
    public List<Budget> getAllBudgetByUnitCodeYear(String codeUL, String year) {

        return budgetRepository.getAllBudgetByUnitCodeYear(codeUL, year);
    }

    @Override
    public Page<Budget> getAllBudgets(Pageable pageRequest) {
        return budgetRepository.findAll(pageRequest);
    }

    @Override
    public Page<Budget> getAllBudgetByUnitCodeYear(String codeUL, String year, Pageable page) {
        return budgetRepository.getAllBudgetByUnitCodeYear(codeUL, year, page);
    }
}
