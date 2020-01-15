package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * Service de gestion de role dans l'application
 */
public interface RoleService {

    List<Role> getAllActiveRoles();

    Optional<Role> getRoleById(Long id);

    Optional<Role> getRoleByLibelle(String libelle);

    Role updateRole(Role role);
}
