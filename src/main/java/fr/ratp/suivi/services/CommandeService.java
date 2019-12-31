package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Commande;

import java.util.List;

public interface CommandeService {

    List<Commande> getAllCommandByUnitCodeYear(String localUnitCode, String annee);
}
