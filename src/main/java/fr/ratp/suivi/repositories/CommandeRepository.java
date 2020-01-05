package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    @Query("SELECT c FROM Commande c WHERE c.localUnit.code = ?1 AND c.annee = ?2")
    List<Commande> getAllBudgetByUnitCodeYear(String localUnitCode, String annee);

    @Query("SELECT c FROM Commande c WHERE c.localUnit.code = ?1 AND c.annee = ?2")
    Page<Commande> getAllBudgetByUnitCodeYear(String codeUL, String year, Pageable page);

}
