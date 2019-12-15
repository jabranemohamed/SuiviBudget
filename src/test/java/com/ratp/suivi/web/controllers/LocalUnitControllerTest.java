package com.ratp.suivi.web.controllers;

import com.ratp.suivi.domain.LocalUnit;
import com.ratp.suivi.services.LocalUnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LocalUnitControllerTest {
    @Mock
    LocalUnitService localUnitService;

    @InjectMocks
    LocalUnitController localUnitController;

    MockMvc mockMvc;

    List<LocalUnit> listUnit = new ArrayList<>();


    @BeforeEach
    void setUp() {
        LocalUnit lu1 = new LocalUnit().builder().code("SPD").description("Systèmes Décisionnels et de Pilotage").build();
        LocalUnit lu2 = new LocalUnit().builder().code("ECO").description("Systèmes économiques").build();
        listUnit.add(lu2);
        listUnit.add(lu1);

        mockMvc = MockMvcBuilders.standaloneSetup(localUnitController).build();
    }

    @Test
    void getAllLocalUnit() throws Exception {
        given(localUnitService.getAllLocalUnit()).willReturn(listUnit);
        mockMvc.perform(get("/api/v1/localUnites"))
                .andExpect(status().isOk());
    }
}
