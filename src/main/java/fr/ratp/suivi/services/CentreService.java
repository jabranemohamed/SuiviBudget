package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Centre;

import java.util.List;
import java.util.Optional;

public interface CentreService {

    List<Centre> findAllActiveCentre();

    Optional<Centre> findCentreByCode(String code);
}
