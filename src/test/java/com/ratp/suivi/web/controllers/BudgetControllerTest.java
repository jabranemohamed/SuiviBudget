package com.ratp.suivi.web.controllers;

import com.ratp.suivi.domain.Budget;
import com.ratp.suivi.services.BudgetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BudgetControllerTest {
    @Mock
    BudgetService budgetService;

    @InjectMocks
    BudgetController budgetController;

    MockMvc mockMvc;

    List<Budget> budgetList = new ArrayList<>();

    Page<Budget> pagedTasks;

    @BeforeEach
    void setUp() {
        Budget b1 = new Budget().builder().annee("2019").build();
        Budget b2 = new Budget().builder().annee("2019").build();
        budgetList.add(b1);
        budgetList.add(b2);
        pagedTasks = new PageImpl(budgetList);

        mockMvc = MockMvcBuilders.standaloneSetup(budgetController).build();
    }

    @Test
    void getAllBudget() throws Exception {
        given(budgetService.getAllBudgets(org.mockito.Matchers.isA(Pageable.class))).willReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/budgets"))
                .andExpect(status().isOk());
    }

}
