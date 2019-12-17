package fr.ratp.suivi.services.fileUpload.beans;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Bean Role correspondant à ligne dans le fichier Role.csv
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class RoleBean {

    @CsvBindByPosition(position = 0)
    private String libelle;

    @CsvBindByPosition(position = 1)
    private String description;
}
