package fr.ratp.suivi.repositories;

import fr.ratp.suivi.domain.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    @Query("SELECT b FROM Budget b WHERE b.localUnit.code = ?1")
    List<Budget> getAllBudgetByUnitCode(String localUnitCode);

    @Query("SELECT b FROM Budget b WHERE b.annee = ?1")
    List<Budget> getAllBudgetByYear(String annee);

    @Query("SELECT b FROM Budget b WHERE b.localUnit.code = ?1 AND b.annee = ?2")
    List<Budget> getAllBudgetByUnitCodeYear(String localUnitCode, String annee);

    @Query("SELECT b FROM Budget b WHERE b.localUnit.code = ?1 AND b.annee = ?2")
    Page<Budget> getAllBudgetByUnitCodeYear(String codeUL, String year, Pageable page);
}
