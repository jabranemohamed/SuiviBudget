package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByLibelle(String libelle);

}