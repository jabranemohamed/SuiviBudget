package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CentreRepository extends JpaRepository<Centre, String> {

    @Query("SELECT c FROM Centre c WHERE c.isActive = true ORDER BY c.code")
    List<Centre> findAllActive();

    Optional<Centre> findByCode(String code);


}

