package com.ratp.suivi.services;

import com.ratp.suivi.domain.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Service de gestion des budgets dans l'application
 */
public interface BudgetService {

    List<Budget> getAllBudgetByUnitCode(String localUnitCode);

    List<Budget> getAllBudgetByYear(String annee);

    List<Budget> getAllBudgetByUnitCodeYear(String localUnitCode, String annee);

    Page<Budget> getAllBudgets(PageRequest pageRequest);

    Page<Budget> getAllBudgetsByYearAndUnit(String codeUL, String year, PageRequest page);
}
