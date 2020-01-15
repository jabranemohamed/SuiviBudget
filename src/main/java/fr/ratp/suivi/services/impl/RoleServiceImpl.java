package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllActiveRoles() {
        return roleRepository.findAllActive();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getRoleByLibelle(String libelle) {
        return roleRepository.findByLibelleAndIsActive(libelle, true);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
}
