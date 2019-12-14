package com.ratp.suivi.services;

import com.ratp.suivi.repositories.BudgetRepository;
import com.ratp.suivi.services.impl.BudgetServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BudgetServiceImplTest {

    @Mock
    BudgetRepository budgetRepository;

    @InjectMocks
    BudgetServiceImpl budgetService;

    @Test
    @DisplayName("")
    void getAllBudgetByUnitCode() {
        budgetService.getAllBudgetByUnitCode("AZ");
    }

    @Test
    void getAllBudgetByYear() {
    }

    @Test
    void getAllBudgetByUnitCodeYear() {
    }

    @Test
    void getAllBudgets() {
    }

    @Test
    void getAllBudgetsByYearAndUnit() {
    }
}