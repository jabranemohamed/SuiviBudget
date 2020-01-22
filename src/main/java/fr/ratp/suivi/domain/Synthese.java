package fr.ratp.suivi.domain;

import lombok.*;

import java.math.BigDecimal;

/**
 * Un POJO qui represente la synthese d'un pudget.
 * <p>
 * Cette class n'est pas persisté car elle est calculable à partir des budgets et des commandes
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Synthese {

    /**
     * Budget associé à la synthese
     */
    private Budget budget;

    /**
     * Le total engagé sur ce budget
     */
    private BigDecimal engage;

    /**
     * Le total receptionner sur ce budgt
     */
    private BigDecimal receptionne;

    /**
     * Le total de reste à réceptionne sur ce budget
     */
    private BigDecimal reste_a_receptionne;


}
