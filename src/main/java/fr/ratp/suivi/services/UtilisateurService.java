package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    List<Utilisateur> findAllUser();

    Optional<Utilisateur> findUserByMatricule(String matricule);

    Optional<Utilisateur> findUserById(Long id);

    Optional<Utilisateur> findUserByUserName(String userName);

    void deleteUsers(List<Utilisateur> users);

    void deleteUser(Utilisateur user);

    Page<Utilisateur> findAllUser(Pageable pageRequest);

    Utilisateur signin(String username, String password);

    Utilisateur signup(Utilisateur user);

    Utilisateur update(Utilisateur user);
}
