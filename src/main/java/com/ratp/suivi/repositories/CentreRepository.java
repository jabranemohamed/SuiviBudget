package com.ratp.suivi.repositories;

import com.ratp.suivi.domain.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CentreRepository extends JpaRepository<Centre, Long> {

    Optional<Centre> getByLibelle(String libelle);

}

