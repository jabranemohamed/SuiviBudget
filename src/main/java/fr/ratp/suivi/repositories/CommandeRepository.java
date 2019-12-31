package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
