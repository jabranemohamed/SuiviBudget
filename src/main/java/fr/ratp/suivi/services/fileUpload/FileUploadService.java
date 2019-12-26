package fr.ratp.suivi.services.fileUpload;

import com.opencsv.bean.CsvToBeanBuilder;
import fr.ratp.suivi.domain.Centre;
import fr.ratp.suivi.domain.LocalUnit;
import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.domain.Utilisateur;
import fr.ratp.suivi.repositories.CentreRepository;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.repositories.UtilisateurRepository;
import fr.ratp.suivi.services.fileUpload.beans.CentreBean;
import fr.ratp.suivi.services.fileUpload.beans.RoleBean;
import fr.ratp.suivi.services.fileUpload.beans.UtilisateurBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Service d'import des données d'un fichier CSV dans la base en mappant avec le POJO correspondant
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FileUploadService {

    @Autowired
    private final CentreRepository centreRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final LocalUnitRepository localUnitRepository;

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    public static final String BEAN_PACKAGE_PATH = "fr.ratp.suivi.services.fileUpload.beans.";

    public void uploadFile(MultipartFile multipartFile, String PojoName) throws IOException, ClassNotFoundException {
        Class<?> aClass = Class.forName(BEAN_PACKAGE_PATH + PojoName + "Bean");
        File file = convertMultiPartToFile(multipartFile);
        file.deleteOnExit();
        List beans = new CsvToBeanBuilder(new FileReader(file))
                .withSeparator(';')
                .withType(aClass)
                .withVerifyReader(true)
                .withThrowExceptions(true)
                .build()
                .parse();
        updateRepositories(beans, PojoName);
    }

    private void updateRepositories(List beans, String pojoName) {
        switch (pojoName) {
            case "Role":
                importRolesData(beans);
                break;
            case "Budget":
                importBudgetData(beans);
                break;
            case "Centre":
                importCentreData(beans);
                break;
            case "Utilisateur":
                importUserData(beans);
                break;
            default:
                System.out.println("Class not found");
        }
    }

    private void importUserData(List beans) {
        List<Utilisateur> existingUsers = utilisateurRepository.findAll();
        List<UtilisateurBean> utilisateurFromCSVFile = (List<UtilisateurBean>) beans;
        List<Utilisateur> utilisateurToCreateOrUpdate = new ArrayList<>();

        utilisateurFromCSVFile.stream().forEach(utilisateurCSV -> {
            boolean b = existingUsers.stream().anyMatch(user -> user.getMatricule().equals(utilisateurCSV.getMatricule()));
            if (!b) { //User does not exist
                Optional<LocalUnit> localUnit = localUnitRepository.findByCode(utilisateurCSV.getLocalUnit());
                Optional<Role> localRole = roleRepository.findByLibelle(utilisateurCSV.getRole());
                if (localUnit.isPresent() && localRole.isPresent()) {
                    Utilisateur newUser = new Utilisateur().builder()
                            .nom(utilisateurCSV.getNom())
                            .prenom(utilisateurCSV.getPrenom())
                            .matricule(utilisateurCSV.getMatricule())
                            .localUnit(localUnit.get())
                            .role(localRole.get())
                            .isActive(true)
                            .build();
                    utilisateurToCreateOrUpdate.add(newUser);
                }

            }
        });
        //Verify if what exist in dataBase is still in the CSV file otherwise desactivate them
        existingUsers.stream().forEach(user -> {
            boolean b = utilisateurFromCSVFile.stream().anyMatch(userCSV -> user.getMatricule().equals(userCSV.getMatricule()));
            if (!b) {
                user.setIsActive(false);
                utilisateurToCreateOrUpdate.add(user);
            }

        });
        if (!utilisateurToCreateOrUpdate.isEmpty())
            utilisateurRepository.saveAll(utilisateurToCreateOrUpdate);

    }

    /**
     * Import
     *
     * @param beans
     */
    private void importCentreData(List beans) {
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

    }

    private void importBudgetData(List beans) {


    }

    private void importRolesData(List beans) {
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
        //Verify if what exist in dataBase is still in the CSV file otherwise desactivate them
        existingRole.stream().forEach(role -> {
            boolean b = roleFromCSVFile.stream().anyMatch(roleCSV -> role.getLibelle().equals(roleCSV.getLibelle()));
            if (!b) {
                role.setIsActive(false);
                rolesToCreateOrUpdate.add(role);
            }

        });
        if (!rolesToCreateOrUpdate.isEmpty())
            roleRepository.saveAll(rolesToCreateOrUpdate);

    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getName());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
