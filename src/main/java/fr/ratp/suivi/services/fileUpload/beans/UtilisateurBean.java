package fr.ratp.suivi.services.fileUpload.beans;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UtilisateurBean {

    @CsvBindByPosition(position = 0)
    private String matricule;

    @CsvBindByPosition(position = 1)
    private String nom;

    @CsvBindByPosition(position = 2)
    private String prenom;

    @CsvBindByPosition(position = 3)
    private String role;

    @CsvBindByPosition(position = 4)
    private String localUnit;
}
