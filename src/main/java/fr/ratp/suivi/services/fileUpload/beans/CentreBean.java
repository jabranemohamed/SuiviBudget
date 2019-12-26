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
public class CentreBean {

    @CsvBindByPosition(position = 0)
    private String centreCode;

    @CsvBindByPosition(position = 1)
    private String unitCode;
}
