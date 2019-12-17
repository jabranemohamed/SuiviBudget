package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Utilisateur;
import fr.ratp.suivi.services.UtilisateurService;
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
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UtilisateurControllerTest {

    @Mock
    UtilisateurService utilisateurService;

    @InjectMocks
    UtilisateurController utilisateurController;

    MockMvc mockMvc;

    List<Utilisateur> listUtilisateur = new ArrayList<>();

    Page<Utilisateur> pagedTasks;
    @BeforeEach
    void setUp() {
        Utilisateur user1 = new Utilisateur().builder().matricule("AB123456").build();
        Utilisateur user2 = new Utilisateur().builder().matricule("CA123456").build();
        listUtilisateur.add(user1);
        listUtilisateur.add(user2);
        pagedTasks = new PageImpl(listUtilisateur);

        mockMvc = MockMvcBuilders.standaloneSetup(utilisateurController).build();
    }

    @Test
    void getAllUtilisateur() throws Exception {
        given(utilisateurService.getAllUser(org.mockito.Matchers.isA(Pageable.class))).willReturn(pagedTasks);
        mockMvc.perform(get("/api/v1/utilisateurs"))
                .andExpect(status().isOk());
    }

    @Test
    void getUtilisateurByMatricule() throws Exception{
        Utilisateur user1 = new Utilisateur().builder().matricule("AB123456").build();
        given(utilisateurService.getUserByMatricule("AB123456")).willReturn(Optional.of(user1));
        mockMvc.perform(get("/api/v1/utilisateurs/{matricule}","AB123456"))
                .andExpect(status().isOk());
    }
}
