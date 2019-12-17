package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service de gestion de role dans l'application
 */
public interface RoleService {

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

    Optional<Role> getRoleByLibelle(String libelle);

    Page<Role> getAllRoles(Pageable page);
}
