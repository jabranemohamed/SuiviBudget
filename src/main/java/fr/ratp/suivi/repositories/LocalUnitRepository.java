package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.LocalUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalUnitRepository extends JpaRepository<LocalUnit, Long> {
}
