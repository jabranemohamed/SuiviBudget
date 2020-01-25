package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Synthese;

import java.util.List;

public interface SyntheseService {

    List<Synthese> findListOfSynthese(String codeUL, String year);
}
