package com.ratp.suivi.services;

import com.ratp.suivi.domain.Centre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface CentreService {

    List<Centre> getAllCentre();

    Optional<Centre> getCentreById(Long id);

    Optional<Centre> getCentreByLibelle(String libelle);

    Page<Centre> getAllCentre(PageRequest page);
}
