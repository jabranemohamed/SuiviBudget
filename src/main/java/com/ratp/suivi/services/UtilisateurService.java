package com.ratp.suivi.services;

import com.ratp.suivi.domain.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    List<Utilisateur> getAllUser();

    Optional<Utilisateur> getUserByMatricule(String matricule);

    Optional<Utilisateur> getUserById(Long id);

    void deleteUsers(List<Utilisateur> users);

    void deleteUser(Utilisateur user);

    Page<Utilisateur> getAllUser(PageRequest pageRequest);
}