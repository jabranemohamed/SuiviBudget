package fr.ratp.suivi.services.fileUpload;

import com.opencsv.bean.CsvToBeanBuilder;
import fr.ratp.suivi.repositories.CentreRepository;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.repositories.UtilisateurRepository;
import fr.ratp.suivi.services.fileUpload.importer.BudgetImporter;
import fr.ratp.suivi.services.fileUpload.importer.CenterImporter;
import fr.ratp.suivi.services.fileUpload.importer.RoleImporter;
import fr.ratp.suivi.services.fileUpload.importer.UtilisateurImporter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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

    @Autowired
    private final LocalUnitRepository localUnitRepository;

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    public static final String BEAN_PACKAGE_PATH = "fr.ratp.suivi.services.fileUpload.beans.";
    public static final String IMPORTER_PACKAGE_PATH = "fr.ratp.suivi.services.fileUpload.importer.";

    public void uploadFile(MultipartFile multipartFile, String PojoName) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
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
                RoleImporter roleImporter = new RoleImporter(roleRepository);
                roleImporter.importData(beans);
                break;
            case "Budget":
                BudgetImporter budgetImporter = new BudgetImporter();
                budgetImporter.importData(beans);
                break;
            case "Centre":
                CenterImporter centerImporter = new CenterImporter(centreRepository, localUnitRepository);
                centerImporter.importData(beans);
                break;
            case "Utilisateur":
                UtilisateurImporter utilisateurImporter = new UtilisateurImporter(utilisateurRepository, roleRepository, localUnitRepository);
                utilisateurImporter.importData(beans);
                break;
            default:
                System.out.println("Class not found");
        }
    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getName());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
