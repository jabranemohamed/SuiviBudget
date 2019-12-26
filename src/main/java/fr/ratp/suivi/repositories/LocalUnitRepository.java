package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.LocalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalUnitRepository extends JpaRepository<LocalUnit, Long> {

    Optional<LocalUnit> findByCode(String code);
}
