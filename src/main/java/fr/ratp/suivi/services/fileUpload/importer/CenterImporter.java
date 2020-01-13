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

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CenterImporter extends BaseImporter {

    @Autowired
    private  final CentreRepository centreRepository;

    @Autowired
    private final LocalUnitRepository localUnitRepository;


    @Override
    public Boolean importData(List beans) {
        List<Centre> existingCentre = centreRepository.findAll();
        List<CentreBean> centreFromCSVFile = (List<CentreBean>) beans;
        List<Centre> centreToCreateOrUpdate = new ArrayList<>();

        centreFromCSVFile.stream().forEach(centreCSV -> {
            boolean b = existingCentre.stream().anyMatch(centre -> centre.getCode().equals(centreCSV.getCentreCode()));
            if (!b) { //Centre does not exist
                Optional<LocalUnit> localUnitCode = localUnitRepository.findByCode(centreCSV.getUnitCode());
                if (localUnitCode.isPresent()) {
                    Centre newCentre = new Centre().builder().code(centreCSV.getCentreCode()).localUnit(localUnitCode.get())
                            .isActive(true).build();
                    centreToCreateOrUpdate.add(newCentre);
                }

            }
        });
        //Verify if what exist in dataBase is still in the CSV file otherwise desactivate them
        existingCentre.stream().forEach(centre -> {
            boolean b = centreFromCSVFile.stream().anyMatch(centreCSV -> centre.getCode().equals(centreCSV.getCentreCode()));
            if (!b) {
                centre.setIsActive(false);
                centreToCreateOrUpdate.add(centre);
            }

        });
        if (!centreToCreateOrUpdate.isEmpty())
            centreRepository.saveAll(centreToCreateOrUpdate);

        return true;

    }
}
