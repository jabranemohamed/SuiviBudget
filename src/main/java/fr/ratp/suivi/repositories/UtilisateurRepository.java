package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Page<Utilisateur> findAll(Pageable pageable);

    Optional<Utilisateur> findByMatricule(String matricule);

    Optional<Utilisateur> findByUsername(String userName);

    @Query("SELECT u FROM Utilisateur u WHERE u.isActive = true")
    List<Utilisateur> findAllActiveUsers();
}
