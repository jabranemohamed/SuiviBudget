package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.Budget;
import fr.ratp.suivi.domain.Commande;
import fr.ratp.suivi.domain.Synthese;
import fr.ratp.suivi.repositories.BudgetRepository;
import fr.ratp.suivi.services.SyntheseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SyntheseServiceImpl implements SyntheseService {

    @Autowired
    private final BudgetRepository budgetRepository;

    @Override
    public List<Synthese> getListOfSynthese(String codeUL, String year) {
        List<Budget> allBudgetByUnitCodeYear = budgetRepository.getAllBudgetByUnitCodeYear(codeUL, year);
        return allBudgetByUnitCodeYear.stream().map(SyntheseServiceImpl::mapBudgetToSynthese).collect(Collectors.toList());
    }

    public static Synthese mapBudgetToSynthese(Budget budget) {
        Synthese synthese = new Synthese();
        synthese.setBudget(budget);
        List<Commande> commande = budget.getCommandes();
        BigDecimal total_a_receptionner = commande
                .stream()
                .map(c -> c.getReste_a_receptionner())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        synthese.setReste_a_receptionne(total_a_receptionner);

        BigDecimal total_receptionner = commande
                .stream()
                .map(c -> c.getQuantite_receptionnee())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        synthese.setReceptionne(total_receptionner);

        BigDecimal total_engage = commande
                .stream()
                .map(c -> c.getQte_engagee_annee_en_cours())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        synthese.setEngage(total_engage);

        return synthese;
    }
}
