package fr.ratp.suivi.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * POJO qui represente une Command
 *
 * @author jabranemohamed
 * @version 1.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Commande {

    @Id
    @Column(unique = true, nullable = false)
    private String id_dist_cmd;

    @Column(nullable = false)
    private String id_entet_cmd;

    @Column(nullable = false)
    private String numero_ols;

    @Column(nullable = false)
    private String fournisseur;

    @Column(nullable = false)
    private String description_commande;

    @Column(nullable = false)
    private String moe_prescripteur;

    private String grande_activite;

    private String activite;

    private BigDecimal notifie;

    private BigDecimal bud_budgget_estime1;

    private BigDecimal bud_budgget_estime2;

    private BigDecimal bud_budgget_estime3;

    private BigDecimal bud_budgget_estime4;

    private BigDecimal qte_engagee_annee_en_cours;

    private BigDecimal reste_a_receptionner;

    private BigDecimal quantite_commandee;

    private BigDecimal quantite_receptionnee;

    private BigDecimal quantite_facturee;

    private BigDecimal recep_avant_annee_en_cours;

    private Date date_creation_commande;

    private String segment1;

    private String segment2;

    private String segment3;

    private String segment4;

    private String segment5;

    private String segment6;

    private String segment7;

    private String catgory;

    private String marche;

    private String desc_ligne_cmd;

    private boolean cmd_a_regulariser;

    private BigDecimal montant_Regularise;

    private boolean cmd_regularisee;

    private String nouveau_segment7;

    @ManyToOne
    @JoinColumn(name = "localUnit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_LocalUnit"))
    private LocalUnit localUnit;

    private String annee;

    @ManyToOne(fetch = FetchType.LAZY)
    private Budget budget;

}
