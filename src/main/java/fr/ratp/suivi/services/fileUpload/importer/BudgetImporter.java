package fr.ratp.suivi.services.fileUpload.importer;

import fr.ratp.suivi.domain.Budget;
import fr.ratp.suivi.domain.BudgetId;
import fr.ratp.suivi.repositories.BudgetRepository;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.services.fileUpload.beans.BudgetBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BudgetImporter extends BaseImporter {

    @Autowired
    private final BudgetRepository budgetRepository;

    @Autowired
    private final LocalUnitRepository localUnitRepository;

    @Override
    public Boolean importData(List beans) {
        List<Budget> allBudgetFromDB = budgetRepository.findAll();
        List<BudgetBean> allBudgetFromCSVFile = (List<BudgetBean>) beans;
        List<Budget> budgetToCreateOrUpdate = new ArrayList<>();

        allBudgetFromCSVFile.stream().forEach(budgetFromCSV -> {
                    boolean exist = allBudgetFromDB
                            .stream()
                            .anyMatch(budget -> budget.getBudgetId().getGrandeActivite().equals(budgetFromCSV.getGrandeActivite())
                                    && budget.getBudgetId().getActivite().equals(budgetFromCSV.getActivite()));
                    if (!exist) {
                        Budget newBudget = new Budget()
                                .builder()
                                .budgetId(new BudgetId().builder().activite(budgetFromCSV.getActivite()).grandeActivite(budgetFromCSV.getGrandeActivite()).build())
                                .budget_notifie(budgetFromCSV.getBudget_notifie())
                                .annee(budgetFromCSV.getAnnee())
                                .estime1(budgetFromCSV.getEstime1())
                                .estime2(budgetFromCSV.getEstime2())
                                .estime3(budgetFromCSV.getEstime3())
                                .estime4(budgetFromCSV.getEstime4())
                                .localUnit(localUnitRepository.findByCode(budgetFromCSV.getLocalUnit()).get()).build();
                        budgetToCreateOrUpdate.add(newBudget);
                    }
                }
        );

        //Verify if what exist in dataBase is still in the CSV file otherwise desactivate them
        allBudgetFromDB.stream().forEach(budget -> {
            Optional<BudgetBean> budgetFromCSVFile = allBudgetFromCSVFile
                    .stream()
                    .filter(budgetCSV -> budget.getBudgetId().getGrandeActivite().equals(budgetCSV.getGrandeActivite()) &&
                            budget.getBudgetId().getActivite().equals(budgetCSV.getActivite()))
                    .findFirst();

            if (budgetFromCSVFile.isPresent()) {//Le budget exist dans la base et dans CSV donc on le met à jour
                BudgetBean budgetBean = budgetFromCSVFile.get();
                budget.setAnnee(budgetBean.getAnnee());
                budget.setBudget_notifie(budgetBean.getBudget_notifie());
                budget.setEstime1(budgetBean.getEstime1());
                budget.setEstime2(budgetBean.getEstime2());
                budget.setEstime3(budgetBean.getEstime3());
                budget.setEstime4(budgetBean.getEstime4());
                budget.setLocalUnit(localUnitRepository.findByCode(budgetBean.getLocalUnit()).get());
                budgetToCreateOrUpdate.add(budget);
            }
        });

        if (!budgetToCreateOrUpdate.isEmpty())
            budgetRepository.saveAll(budgetToCreateOrUpdate);
        return true;
    }
}
