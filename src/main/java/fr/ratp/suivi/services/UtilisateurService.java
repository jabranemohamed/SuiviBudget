package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface UtilisateurService {

    List<Utilisateur> getAllUser();

    Optional<Utilisateur> getUserByMatricule(String matricule);

    Optional<Utilisateur> getUserById(Long id);

    void deleteUsers(List<Utilisateur> users);

    void deleteUser(Utilisateur user);

    Page<Utilisateur> getAllUser(Pageable pageRequest);

    Utilisateur signin(String username, String password);

    Utilisateur signup(Utilisateur user);

    Utilisateur whoami(HttpServletRequest req);

    String refresh(String username);
}
