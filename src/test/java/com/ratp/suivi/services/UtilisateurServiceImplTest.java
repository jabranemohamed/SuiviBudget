package com.ratp.suivi.services;

import com.ratp.suivi.domain.Utilisateur;
import com.ratp.suivi.repositories.UtilisateurRepository;
import com.ratp.suivi.services.impl.UtilisateurServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceImplTest {

    public static final String MAT_AB_123456 = "AB123456";
    @Mock
    UtilisateurRepository utilisateurRepository;

    @InjectMocks
    UtilisateurServiceImpl utilisateurService;

    static List<Utilisateur> listUtilisateur;

    static Utilisateur singleUtilisateur;

    @BeforeAll
    public static void init() {
        singleUtilisateur = new Utilisateur().builder()
                .matricule(MAT_AB_123456)
                .nom("Martin")
                .id(1L)
                .build();

        Utilisateur user2 = new Utilisateur().builder()
                .matricule("CA123456")
                .nom("BERNARD")
                .id(2L)
                .build();

        listUtilisateur = new ArrayList();
        listUtilisateur.add(singleUtilisateur);
        listUtilisateur.add(user2);
    }

    @Test
    @DisplayName("Retourner tous les utilisateurs")
    void getAllUser() {
        when(utilisateurRepository.findAll()).thenReturn(listUtilisateur);

        List<Utilisateur> retrievedUser = utilisateurService.getAllUser();
        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.size()).isEqualTo(2);

        verify(utilisateurRepository).findAll();
    }

    @Test
    @DisplayName("Retourner un utilisateur identifié par son matricule")
    void getUserByMatricule() {
        when(utilisateurRepository.findByMatricule(MAT_AB_123456)).thenReturn(Optional.of(singleUtilisateur));

        Optional<Utilisateur> userByMatricule = utilisateurService.getUserByMatricule(MAT_AB_123456);
        assertThat(userByMatricule.get()).isNotNull();
        assertThat(userByMatricule.get().getMatricule()).isEqualTo(MAT_AB_123456);

        verify(utilisateurRepository).findByMatricule(MAT_AB_123456);
    }

    @Test
    @DisplayName("Retourner un utilisateur par son id")
    void getUserById() {
        when(utilisateurRepository.findById(1L)).thenReturn(Optional.of(singleUtilisateur));

        Optional<Utilisateur> userByMatricule = utilisateurService.getUserById(1L);
        assertThat(userByMatricule.get()).isNotNull();
        assertThat(userByMatricule.get().getId()).isEqualTo(1L);

        verify(utilisateurRepository).findById(1L);
    }

    @Test
    @DisplayName("Supprimer une liste des utilisateurs")
    void deleteUsers() {
        utilisateurService.deleteUsers(listUtilisateur);
        verify(utilisateurRepository).deleteAll(anyList());
    }

    @Test
    @DisplayName("Supprimer un utilisateur ")
    void deleteUser() {
        utilisateurService.deleteUser(singleUtilisateur);
        verify(utilisateurRepository).delete(any(Utilisateur.class));
    }


}