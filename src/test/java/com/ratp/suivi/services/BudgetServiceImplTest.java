package com.ratp.suivi.services;

import com.ratp.suivi.domain.Budget;
import com.ratp.suivi.domain.LocalUnit;
import com.ratp.suivi.repositories.BudgetRepository;
import com.ratp.suivi.services.impl.BudgetServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BudgetServiceImplTest {


    @Mock
    BudgetRepository budgetRepository;

    @InjectMocks
    BudgetServiceImpl budgetService;

    static List<Budget> list_budget_2019 = new ArrayList<>();

    static LocalUnit lu;

    @BeforeAll
    public static void init() {

        lu = new LocalUnit().builder().code("SDP").build();
        Budget budget = budgetBuilderInstance("Redevance", "IBM", BigDecimal.valueOf(100000)
                , BigDecimal.valueOf(100000), BigDecimal.valueOf(100000), BigDecimal.valueOf(100000), BigDecimal.valueOf(100000), "2019", lu);

        Budget budget2 = budgetBuilderInstance("Redevance", "Qlik", BigDecimal.valueOf(100000)
                , BigDecimal.valueOf(100000), BigDecimal.valueOf(100000), BigDecimal.valueOf(100000), BigDecimal.valueOf(100000), "2019", lu);

        list_budget_2019.add(budget);
        list_budget_2019.add(budget2);
    }


    @Test
    void getAllBudgetByUnitCode() {

        when(budgetRepository.getAllBudgetByUnitCode("SDP")).thenReturn(list_budget_2019);

        List<Budget> sdpList = budgetService.getAllBudgetByUnitCode("SDP");
        assertThat(sdpList).isNotNull();
        assertThat(sdpList.size()).isEqualTo(2);

        verify(budgetRepository).getAllBudgetByUnitCode("SDP");

    }

    @Test
    void getAllBudgetByYear() {
        when(budgetRepository.getAllBudgetByYear("2019")).thenReturn(list_budget_2019);

        List<Budget> sdpList = budgetService.getAllBudgetByYear("2019");
        assertThat(sdpList).isNotNull();
        assertThat(sdpList.size()).isEqualTo(2);

        verify(budgetRepository).getAllBudgetByYear("2019");
    }

    @Test
    void getAllBudgetByUnitCodeYear() {

        when(budgetRepository.getAllBudgetByUnitCodeYear("SDP", "2019")).thenReturn(list_budget_2019);

        List<Budget> sdpList = budgetService.getAllBudgetByUnitCodeYear("SDP", "2019");
        assertThat(sdpList).isNotNull();
        assertThat(sdpList.size()).isEqualTo(2);

        verify(budgetRepository).getAllBudgetByUnitCodeYear("SDP", "2019");
    }


    private static Budget budgetBuilderInstance(String grandeActivite, String activite, BigDecimal budget_notifie,
                                                BigDecimal estime1, BigDecimal estime2, BigDecimal estime3,
                                                BigDecimal estime4, String annee, LocalUnit lu) {
        return new Budget().builder()
                .activite(activite)
                .annee(annee)
                .estime1(estime1)
                .estime2(estime2)
                .estime3(estime3)
                .budget_notifie(budget_notifie)
                .estime4(estime4)
                .grandeActivite(grandeActivite)
                .localUnit(lu)
                .id(1L)
                .build();
    }
}