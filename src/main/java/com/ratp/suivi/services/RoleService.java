package com.ratp.suivi.services;

import com.ratp.suivi.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * Service de gestion de role dans l'application
 */
public interface RoleService {

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

    Optional<Role> getRoleByLibelle(String libelle);

    Page<Role> getAllRoles(PageRequest page);
}
