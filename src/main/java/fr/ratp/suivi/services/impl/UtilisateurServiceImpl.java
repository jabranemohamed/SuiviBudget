package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.domain.Utilisateur;
import fr.ratp.suivi.exception.CustomException;
import fr.ratp.suivi.jwt.JwtTokenProvider;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.repositories.UtilisateurRepository;
import fr.ratp.suivi.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    private final LocalUnitRepository localUnitRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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
        log.info("Suppression de l'utilisateur de matricule " + user.getMatricule());
    }

    @Override
    public Page<Utilisateur> getAllUser(Pageable pageRequest) {
        return utilisateurRepository.findAll(pageRequest);
    }


    @Override
    public Utilisateur signin(String username, String password) throws AuthenticationException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Optional<Utilisateur> byMatricule = utilisateurRepository.findByMatricule(username);
        Role role = byMatricule.get().getRole();
        String token = jwtTokenProvider.createToken(username, Arrays.asList(role));

        if (byMatricule.isPresent()) {
            byMatricule.get().setToken(token);
            return utilisateurRepository.save(byMatricule.get());
        } else
            return null; //TODO : to change better

    }

    @Override
    public Utilisateur signup(Utilisateur user) {
        if (!utilisateurRepository.findByMatricule(user.getMatricule()).isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (user.getLocalUnit() == null) //HardCoded till there are logic
                user.setLocalUnit(localUnitRepository.findAll().get(0));
            if (user.getRole() == null) //HardCoded till there are logic
                user.setRole(roleRepository.findAll().get(0));

            String token = jwtTokenProvider.createToken(user.getMatricule(), Arrays.asList(user.getRole()));
            user.setToken(token);
            return utilisateurRepository.save(user);

        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public Utilisateur whoami(HttpServletRequest req) {
        return utilisateurRepository.findByMatricule(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))).get();
    }

    @Override
    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, Arrays.asList(utilisateurRepository.findByMatricule(username).get().getRole()));
    }

}
