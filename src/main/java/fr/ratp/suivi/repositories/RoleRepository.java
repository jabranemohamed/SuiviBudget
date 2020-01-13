package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByLibelleAndIsActive(String libelle, Boolean isActive);

    @Query("SELECT r FROM Role r WHERE r.isActive = true")
    List<Role> findAllActive();
    
}
