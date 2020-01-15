package fr.ratp.suivi.services.fileUpload.beans;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class BudgetBean {

    @CsvBindByPosition(position = 0)
    private String grandeActivite;

    @CsvBindByPosition(position = 1)
    private String activite;

    @CsvBindByPosition(position = 2)
    private BigDecimal budget_notifie;

    @CsvBindByPosition(position = 3)
    private BigDecimal estime1;

    @CsvBindByPosition(position = 4)
    private BigDecimal estime2;

    @CsvBindByPosition(position = 5)
    private BigDecimal estime3;

    @CsvBindByPosition(position = 6)
    private BigDecimal estime4;

    @CsvBindByPosition(position = 7)
    private String localUnit;

    @CsvBindByPosition(position = 8)
    private String annee;

}
