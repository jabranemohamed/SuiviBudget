package fr.ratp.suivi.services.fileUpload.importer;

import fr.ratp.suivi.repositories.BudgetRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@NoArgsConstructor(force = true)
@Transactional
@Slf4j
public class BudgetImporter extends BaseImporter {

    @Autowired
    private final BudgetRepository budgetRepository;

    @Override
    public Boolean importData(List beans) {
        return null;
    }
}
