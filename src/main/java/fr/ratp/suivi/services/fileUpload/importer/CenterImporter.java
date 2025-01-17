package fr.ratp.suivi.services.fileUpload.importer;

import fr.ratp.suivi.domain.Centre;
import fr.ratp.suivi.domain.LocalUnit;
import fr.ratp.suivi.repositories.CentreRepository;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.services.fileUpload.beans.CentreBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CenterImporter extends BaseImporter {

    @Autowired
    private final CentreRepository centreRepository;

    @Autowired
    private final LocalUnitRepository localUnitRepository;


    @Override
    public Boolean importData(List beans) {
        List<Centre> existingCentre = centreRepository.findAll();
        List<CentreBean> centreFromCSVFile = (List<CentreBean>) beans;
        List<Centre> centreToCreateOrUpdate = new ArrayList<>();

        AtomicInteger newCenterCounter = new AtomicInteger();
        log.info("Import centre in_progress....");
        log.info(centreFromCSVFile.size()+" ligne dans le fichier centre à importer");

        centreFromCSVFile.stream().forEach(centreCSV -> {
            boolean b = existingCentre
                    .stream()
                    .anyMatch(centre -> centre.getCode().equals(centreCSV.getCentreCode()));
            if (!b) { //Centre does not exist
                Optional<LocalUnit> localUnitCode = localUnitRepository.findByCode(centreCSV.getUnitCode());
                if (localUnitCode.isPresent()) {
                    Centre newCentre = new Centre().builder().code(centreCSV.getCentreCode()).localUnit(localUnitCode.get())
                            .isActive(true).build();
                    centreToCreateOrUpdate.add(newCentre);
                    newCenterCounter.getAndIncrement();
                }

            }
        });
        //Verify if what exist in dataBase is still in the CSV file otherwise desactivate them
        existingCentre.stream().forEach(centre -> {
            Optional<CentreBean> centerFromCSVFile = centreFromCSVFile
                    .stream()
                    .filter(centreCSV -> centre.getCode().equals(centreCSV.getCentreCode()))
                    .findFirst();
            if (centerFromCSVFile.isPresent()) { //L'object centre existe dans la base et le fichier CSV
                if (!centre.getLocalUnit().getCode().equals(centerFromCSVFile.get().getUnitCode())) {
                    //Si le code d'unité du centre change on doit alors mettre a jour
                    Optional<LocalUnit> retrievedUnit = localUnitRepository.findByCode(centerFromCSVFile.get().getUnitCode());
                    centre.setLocalUnit(retrievedUnit.get());
                    centre.setIsActive(true);
                    centreToCreateOrUpdate.add(centre);
                }
            } else {
                centre.setIsActive(false);
                centreToCreateOrUpdate.add(centre);
            }

        });
        if (!centreToCreateOrUpdate.isEmpty())
            centreRepository.saveAll(centreToCreateOrUpdate);

        log.info("Nombre de nouveaux centre crée : "+ newCenterCounter.get());
        log.info("Fin d'import de fichier centre");
        return true;

    }
}
