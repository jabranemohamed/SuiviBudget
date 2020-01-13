package fr.ratp.suivi.services.fileUpload.importer;

import java.util.List;

/**
 * Class abstract la base de l'import des fichiers CSV
 */
public abstract class BaseImporter {

    public abstract Boolean importData(List beans);

}
