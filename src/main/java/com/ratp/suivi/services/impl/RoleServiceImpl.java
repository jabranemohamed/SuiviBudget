package com.ratp.suivi.services.impl;

import com.ratp.suivi.domain.Role;
import com.ratp.suivi.repositories.RoleRepository;
import com.ratp.suivi.services.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Optional<Role> getRoleByLibelle(String libelle) {

        return roleRepository.findByLibelle(libelle);
    }

    @Override
    public Page<Role> getAllRoles(Pageable page) {
        return roleRepository.findAll(page);
    }
}
