package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Synthese;

import java.util.List;

public interface SyntheseService {

    List<Synthese> getListOfSynthese( String codeUL, String year);
}
