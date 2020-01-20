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
public class CommandeBean {

    @CsvBindByPosition(position = 0)
    private String id_dist_cmd;

    @CsvBindByPosition(position = 1)
    private String id_entet_cmd;

    @CsvBindByPosition(position = 2)
    private String numero_ols;

    @CsvBindByPosition(position = 3)
    private String fournisseur;

    @CsvBindByPosition(position = 4)
    private String description_commande;

    @CsvBindByPosition(position = 5)
    private String moe_prescripteur;

    @CsvBindByPosition(position = 6)
    private String budget_grande_activite;

    @CsvBindByPosition(position = 7)
    private String budget_activite;

    @CsvBindByPosition(position = 8)
    private BigDecimal budget_notifie;

    @CsvBindByPosition(position = 9)
    private BigDecimal bud_budgget_estime1;

    @CsvBindByPosition(position = 10)
    private BigDecimal bud_budgget_estime2;

    @CsvBindByPosition(position = 11)
    private BigDecimal bud_budgget_estime3;

    @CsvBindByPosition(position = 12)
    private BigDecimal bud_budgget_estime4;

    @CsvBindByPosition(position = 13)
    private BigDecimal qte_engagee_annee_en_cours;

    @CsvBindByPosition(position = 14)
    private BigDecimal reste_a_receptionner;

    @CsvBindByPosition(position = 15)
    private BigDecimal quantite_commandee;

    @CsvBindByPosition(position = 16)
    private BigDecimal quantite_receptionnee;

    @CsvBindByPosition(position = 17)
    private BigDecimal quantite_facturee;

    @CsvBindByPosition(position = 18)
    private BigDecimal recep_avant_annee_en_cours;

    @CsvBindByPosition(position = 19)
    private String date_creation_commande;

    @CsvBindByPosition(position = 20)
    private String segment1;

    @CsvBindByPosition(position = 21)
    private String segment2;

    @CsvBindByPosition(position = 22)
    private String segment3;

    @CsvBindByPosition(position = 23)
    private String segment4;

    @CsvBindByPosition(position = 24)
    private String segment5;

    @CsvBindByPosition(position = 25)
    private String segment6;

    @CsvBindByPosition(position = 26)
    private String segment7;

    @CsvBindByPosition(position = 27)
    private String catgory;

    @CsvBindByPosition(position = 28)
    private String marche;

    @CsvBindByPosition(position = 29)
    private String desc_ligne_cmd;

    @CsvBindByPosition(position = 30)
    private String localUnit;

    @CsvBindByPosition(position = 31)
    private String annee;
}
