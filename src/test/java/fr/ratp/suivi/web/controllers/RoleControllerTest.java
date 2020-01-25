package fr.ratp.suivi.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.services.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    @Mock
    RoleService roleService;

    @InjectMocks
    RoleController roleController;

    @InjectMocks
    ObjectMapper objectMapper;

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
    @DisplayName("GET /api/v1/roles - Found")
    void testGetAllActiveRoles() throws Exception {
        given(roleService.findAllActiveRoles()).willReturn(listRole);
        mockMvc.perform(get("/api/v1/roles"))

                //Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].libelle", is("RUL")))
                .andExpect(jsonPath("$.[1].libelle", is("RDO")));
    }

    @Test
    @DisplayName("PUT /api/v1/role - Success")
    void testRoleUpdateSucess() throws Exception {
        Role role1 = new Role().builder().libelle("RUL").description("Responsable unité locale").id(1L).isActive(true).build();
        Role role2 = new Role().builder().libelle("RUL").description("Responsable d'unité locale").id(1L).isActive(true).build();
        given(roleService.updateRole(ArgumentMatchers.any())).willReturn(role2);
        given(roleService.findRoleByLibelle(anyString())).willReturn(Optional.of(role1));

        mockMvc.perform(put("/api/v1/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role1)))

                //Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                //Validate the returned fields
                .andExpect(jsonPath("$.description", is("Responsable d'unité locale")));
    }

    @Test
    @DisplayName("PUT /api/v1/role - Not Found")
    void testRoleUpdateNotFound() throws Exception {
        Role role1 = new Role().builder().libelle("RUL").description("Responsable unité locale").id(1L).isActive(true).build();
        given(roleService.findRoleByLibelle(anyString())).willReturn(Optional.empty());

        mockMvc.perform(put("/api/v1/role")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(role1)))

                //Validate the response code and content type
                .andExpect(status().isNotFound());

    }
}