package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Centre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CentreService {

    List<Centre> getAllCentre();

    Optional<Centre> getCentreById(Long id);

    Page<Centre> getAllCentre(Pageable page);
}
