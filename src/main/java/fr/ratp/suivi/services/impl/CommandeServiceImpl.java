package fr.ratp.suivi.services.impl;

import fr.ratp.suivi.domain.Budget;
import fr.ratp.suivi.domain.Commande;
import fr.ratp.suivi.repositories.BudgetRepository;
import fr.ratp.suivi.repositories.CommandeRepository;
import fr.ratp.suivi.services.CommandeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    private final CommandeRepository commandeRepository;

    @Autowired
    private final BudgetRepository budgetRepository;

    @Override
    public List<Commande> getAllCommandByUnitCodeYear(String localUnitCode, String annee) {
        return commandeRepository.getAllBudgetByUnitCodeYear(localUnitCode, annee);
    }

    @Override
    public Page<Commande> getAllCommandByUnitCodeYear(String codeUL, String year, Pageable page) {
        return commandeRepository.getAllBudgetByUnitCodeYear(codeUL, year, page);
    }

    @Override
    public List<Commande> updateCommandes(List<Commande> commandes) {
        List<Commande> result = new ArrayList<>();
        for (Commande commande : commandes) {
            Commande updateCommandWithBudget = updateCommandWithBudget(commande);
            result.add(updateCommandWithBudget);
        }
        return commandeRepository.saveAll(result);
    }

    public Commande updateCommandWithBudget(Commande commande) {
        if (commande.getGrande_activite() != null && commande.getActivite() != null) {
            Optional<Budget> budgetOpt = budgetRepository.getAllByGrandeActiviteAndActivite(commande.getGrande_activite(), commande.getActivite());
            commande.setBudget(budgetOpt.get());
        }
        return commande;
    }
}
