package fr.ratp.suivi.services.fileUpload;

import com.opencsv.bean.CsvToBeanBuilder;
import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.repositories.CentreRepository;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.services.fileUpload.beans.RoleBean;
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

    public static final String BEAN_PACKAGE_PATH = "fr.ratp.suivi.services.fileUpload.beans.";

    public void uploadFile(MultipartFile multipartFile, String PojoName) throws IOException, ClassNotFoundException {
        Class<?> aClass = Class.forName(BEAN_PACKAGE_PATH + PojoName + "Bean");
        File file = convertMultiPartToFile(multipartFile);
        List beans = new CsvToBeanBuilder(new FileReader(file))
                .withSeparator(';')
                .withType(aClass)
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
            default:
                System.out.println("Color not found");
        }
    }

    private void importBudgetData(List beans) {


    }

    private void importRolesData(List beans) {
        List<Role> existingRole = roleRepository.findAll();
        List<RoleBean> roleFromCSVFile = (List<RoleBean>) beans;
        //List to create or to update
        List<Role> rolesToCreateOrUpdate = new ArrayList<>();
        List<Role> rolesToUpdate = new ArrayList<>();

        roleFromCSVFile.stream().forEach(roleCSV -> {
            boolean b = existingRole.stream().anyMatch(role -> role.getLibelle().equals(roleCSV.getLibelle()));
            if (!b) {
                Role newRole = new Role().builder().description(roleCSV.getDescription()).libelle(roleCSV.getLibelle()).build();
                rolesToCreateOrUpdate.add(newRole);
            }
        });
        //Verify if what exist in dataBase is still in the CSV file otherwise desactivate them
        existingRole.stream().forEach(role->{
            boolean b = roleFromCSVFile.stream().anyMatch(roleCSV -> role.getLibelle().equals(roleCSV.getLibelle()));
            if (!b) {
                role.setIsActive(false);
                rolesToCreateOrUpdate.add(role);
            }

        });
        if(!rolesToCreateOrUpdate.isEmpty())
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
