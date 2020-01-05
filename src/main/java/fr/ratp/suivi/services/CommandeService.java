package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommandeService {

    List<Commande> getAllCommandByUnitCodeYear(String localUnitCode, String annee);

    Page<Commande> getAllCommandByUnitCodeYear(String codeUL, String year, Pageable page);
}
