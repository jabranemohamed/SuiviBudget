package fr.ratp.suivi.services.fileUpload.importer;

import fr.ratp.suivi.domain.LocalUnit;
import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.domain.Utilisateur;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.repositories.UtilisateurRepository;
import fr.ratp.suivi.services.fileUpload.beans.UtilisateurBean;
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
public class UtilisateurImporter extends BaseImporter {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final LocalUnitRepository localUnitRepository;


    @Override
    public Boolean importData(List beans) {
        List<Utilisateur> existingUsers = utilisateurRepository.findAll();
        List<UtilisateurBean> utilisateurFromCSVFile = (List<UtilisateurBean>) beans;
        List<Utilisateur> utilisateurToCreateOrUpdate = new ArrayList<>();

        utilisateurFromCSVFile.stream().forEach(utilisateurCSV -> {
            Optional<Utilisateur> exist = existingUsers.stream().filter(user -> user.getMatricule().equals(utilisateurCSV.getMatricule())).findFirst();
            if (!exist.isPresent()) { //User does not exist
                Optional<LocalUnit> localUnit = localUnitRepository.findByCode(utilisateurCSV.getLocalUnit());
                Optional<Role> localRole = roleRepository.findByLibelleAndIsActive(utilisateurCSV.getRole(),true);
                if (localUnit.isPresent() && localRole.isPresent()) {
                    Utilisateur newUser = new Utilisateur().builder()
                            .nom(utilisateurCSV.getNom())
                            .prenom(utilisateurCSV.getPrenom())
                            .matricule(utilisateurCSV.getMatricule())
                            .localUnit(localUnit.get())
                            .role(localRole.get())
                            .isActive(utilisateurCSV.isActive())
                            .build();
                    utilisateurToCreateOrUpdate.add(newUser);
                }
            }
        });
        //Verify if what exist in dataBase is still in the CSV file otherwise desactivate them
        existingUsers.stream().forEach(user -> {
            Optional<UtilisateurBean>  userFound = utilisateurFromCSVFile
                    .stream()
                    .filter(userCSV -> user.getMatricule().equals(userCSV.getMatricule()))
                    .findFirst();
            if (userFound.isPresent()) {
                user.setIsActive(userFound.get().isActive());

            }else{
                user.setIsActive(false);
            }
            utilisateurToCreateOrUpdate.add(user);
        });
        if (!utilisateurToCreateOrUpdate.isEmpty())
            utilisateurRepository.saveAll(utilisateurToCreateOrUpdate);

        return true;
    }
}
