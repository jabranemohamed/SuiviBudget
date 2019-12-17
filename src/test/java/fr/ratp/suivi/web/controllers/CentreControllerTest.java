package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Centre;
import fr.ratp.suivi.services.CentreService;
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
public class CentreControllerTest {
    @Mock
    CentreService centreService;

    @InjectMocks
    CentreController centreController;

    MockMvc mockMvc;

    List<Centre> centreList = new ArrayList<>();

    Page<Centre> pagedTasks;


    @BeforeEach
    void setUp() {
        Centre lu1 = new Centre().builder().code("01171").build();
        Centre lu2 = new Centre().builder().code("01169").build();
        centreList.add(lu2);
        centreList.add(lu1);
        pagedTasks = new PageImpl(centreList);

        mockMvc = MockMvcBuilders.standaloneSetup(centreController).build();
    }

    @Test
    void getAllCentre() throws Exception {
        given(centreService.getAllCentre(org.mockito.Matchers.isA(Pageable.class))).willReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/centres"))
                .andExpect(status().isOk());
    }
}

