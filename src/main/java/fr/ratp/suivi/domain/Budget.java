package fr.ratp.suivi.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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
public class Budget implements Serializable {

    @EmbeddedId
    private BudgetId budgetId;

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
