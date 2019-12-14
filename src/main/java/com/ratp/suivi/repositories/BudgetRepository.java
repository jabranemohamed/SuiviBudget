package com.ratp.suivi.repositories;

import com.ratp.suivi.domain.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> getAllBudgetByUnitCode(String localUnitCode);

    List<Budget> getAllBudgetByYear(String annee);

    List<Budget> getAllBudgetByUnitCodeYear(String localUnitCode, String annee);

    Page<Budget> getAllBudgets(PageRequest pageRequest);

    Page<Budget> getAllBudgetsByYearAndUnit(String codeUL, String year, PageRequest page);
}
