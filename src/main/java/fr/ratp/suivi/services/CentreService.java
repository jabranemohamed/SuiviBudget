package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Centre;

import java.util.List;
import java.util.Optional;

public interface CentreService {

    List<Centre> getAllActiveCentre();

    Optional<Centre> getCentreByCode(String code);
}
