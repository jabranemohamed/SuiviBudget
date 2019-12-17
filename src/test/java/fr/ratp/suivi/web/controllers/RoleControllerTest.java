package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.services.RoleService;
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
class RoleControllerTest {

    @Mock
    RoleService roleService;

    @InjectMocks
    RoleController roleController;

    MockMvc mockMvc;

    List<Role> listRole = new ArrayList<>();

    Page<Role> pagedTasks;

    @BeforeEach
    void setUp() {
        Role role1 = new Role().builder().libelle("RUL").description("Responsable d'unité locale").build();
        Role role2 = new Role().builder().libelle("RDO").description("Responsable de domaine").build();
        listRole.add(role1);
        listRole.add(role2);
        pagedTasks = new PageImpl(listRole);

        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    void getAllRole() throws Exception {
        given(roleService.getAllRoles(org.mockito.Matchers.isA(Pageable.class))).willReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/roles"))
                .andExpect(status().isOk());
    }
}