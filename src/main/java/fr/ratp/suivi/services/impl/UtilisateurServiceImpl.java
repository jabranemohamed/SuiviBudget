package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.Utilisateur;
import fr.ratp.suivi.repositories.UtilisateurRepository;
import fr.ratp.suivi.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public List<Utilisateur> getAllUser() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Optional<Utilisateur> getUserByMatricule(String matricule) {
        return utilisateurRepository.findByMatricule(matricule);
    }

    @Override
    public Optional<Utilisateur> getUserById(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public void deleteUsers(List<Utilisateur> users) {
        utilisateurRepository.deleteAll(users);
    }

    @Override
    public void deleteUser(Utilisateur user) {
        utilisateurRepository.delete(user);
        log.info("Suppression de l'utilisateur de matricule "+user.getMatricule());
    }

    @Override
    public Page<Utilisateur> getAllUser(Pageable pageRequest) {
        return utilisateurRepository.findAll(pageRequest);
    }
}
