package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Role;

import java.util.List;
import java.util.Optional;

/**
 * Service de gestion de role dans l'application
 */
public interface RoleService {

    List<Role> findAllActiveRoles();

    Optional<Role> findRoleById(Long id);

    Optional<Role> findRoleByLibelle(String libelle);

    Role updateRole(Role role);
}
