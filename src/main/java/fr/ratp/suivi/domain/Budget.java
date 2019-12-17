package fr.ratp.suivi.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Un POJO qui represente un budget d'un activité dans une unité local dans une année
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
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    //A better design to change to OneRef to an other POJO or ENUM but for now we keep it simple as String
    @Column(nullable = false)
    private String grandeActivite;

    private String activite;

    private BigDecimal budget_notifie;

    private BigDecimal estime1;

    private BigDecimal estime2;

    private BigDecimal estime3;

    private BigDecimal estime4;

    private String annee;

    @ManyToOne
    @JoinColumn(name = "localUnit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_LocalUnit"))
    private LocalUnit localUnit;


}
