package com.ratp.suivi.services;

import com.ratp.suivi.domain.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service de gestion des budgets dans l'application
 */
public interface BudgetService {

    List<Budget> getAllBudgetByUnitCode(String localUnitCode);

    List<Budget> getAllBudgetByYear(String annee);

    List<Budget> getAllBudgetByUnitCodeYear(String localUnitCode, String annee);

    Page<Budget> getAllBudgets(Pageable pageRequest);

    Page<Budget> getAllBudgetsByYearAndUnit(String codeUL, String year, Pageable page);
}
