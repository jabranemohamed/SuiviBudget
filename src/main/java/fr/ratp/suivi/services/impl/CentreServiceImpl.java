package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.Centre;
import fr.ratp.suivi.repositories.CentreRepository;
import fr.ratp.suivi.services.CentreService;
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
public class CentreServiceImpl implements CentreService {

    @Autowired
    private final CentreRepository centreRepository;

    @Override
    public List<Centre> getAllCentre() {

        return centreRepository.findAll();
    }

    @Override
    public Optional<Centre> getCentreById(Long id) {

        return centreRepository.findById(id);
    }

    @Override
    public Page<Centre> getAllCentre(Pageable page) {

        return centreRepository.findAll(page);
    }
}
