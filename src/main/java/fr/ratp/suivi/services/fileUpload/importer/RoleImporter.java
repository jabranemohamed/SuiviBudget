package fr.ratp.suivi.services.fileUpload.importer;

import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.services.fileUpload.beans.RoleBean;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class d'import du fichier Role.csv
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleImporter extends BaseImporter {

    @Autowired
    private final RoleRepository roleRepository;


    /**
     * Importer les objets lu du fichier csv
     * La logique d'import est comme suit :
     * <ul>
     * <li>Si un role du fichier CSV n'existe pas dans la base alors on l'importe dans la base comme un nouveau object
     * <li>Si l'object existe , il y'a une comparison et une difference alors c'est l'object importer qui est enregistrer
     * <li>Si un object dans la BD n'existe plus parmi les objects issue de fichier CVS alors ils sont considerer plus actif à is_deleted </li>
     * </ul>
     *
     * @param beans list des roles venant du fichier CSV
     * @return
     */
    @Override
    public Boolean importData(List beans) {
        List<Role> existingRole = roleRepository.findAll();
        List<RoleBean> roleFromCSVFile = (List<RoleBean>) beans;
        //List to create or to update
        List<Role> rolesToCreateOrUpdate = new ArrayList<>();

        roleFromCSVFile.stream().forEach(roleCSV -> {
            boolean b = existingRole.stream().anyMatch(role -> role.getLibelle().equals(roleCSV.getLibelle()));
            if (!b) {
                Role newRole = new Role().builder().isActive(true).description(roleCSV.getDescription()).libelle(roleCSV.getLibelle()).build();
                rolesToCreateOrUpdate.add(newRole);
            }
        });
        //Verifié si ce que exist dans la base est comme celui du fichier CSV
        existingRole.stream().forEach(role -> {
            Optional<RoleBean> roleFound = roleFromCSVFile.stream().filter(roleCSV -> role.getLibelle().equals(roleCSV.getLibelle())).findFirst();
            if (roleFound.isPresent()) { //L'object exist dans la base et dans le fichier
                role.setDescription(roleFound.get().getDescription());
                role.setIsActive(true);
                rolesToCreateOrUpdate.add(role);
            } else {
                role.setIsActive(false);
                rolesToCreateOrUpdate.add(role);
            }

        });
        if (!rolesToCreateOrUpdate.isEmpty())
            roleRepository.saveAll(rolesToCreateOrUpdate);

        return true;

    }
}
