package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.Commande;
import fr.ratp.suivi.repositories.CentreRepository;
import fr.ratp.suivi.services.CommandeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private final CentreRepository centreRepository;

    @Override
    public List<Commande> getAllCommandByUnitCodeYear(String localUnitCode, String annee) {
        return null;
    }
}
