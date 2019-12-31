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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

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

    private BigDecimal qte_engagee_annee_en_cours;

    private BigDecimal reste_a_receptionner;

    private BigDecimal quantite_commandee;

    private BigDecimal quantite_receptionnee;

    private BigDecimal quantite_facturee;

    private BigDecimal recep_avant_annee_en_cours;

    private Date date_creation_commande;

    private BigDecimal segment1;

    private BigDecimal segment2;

    private BigDecimal segment3;

    private BigDecimal segment4;

    private BigDecimal segment5;

    private BigDecimal segment6;

    private BigDecimal segment7;

    private String catgory_marche;

    private String desc_ligne_cmd;

    private String cmd_a_regulariser;

    private String cmd_regularisee;

    private String nouveau_segment7;

    @ManyToOne
    private LocalUnit localUnit;

    private String annee;

}
