package fr.ratp.suivi.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statistics {

    private String grandeActivite;

    /**
     * Le total engagé sur ce budget
     */
    private BigDecimal engage = BigDecimal.ZERO;

    /**
     * Le total receptionner sur ce budgt
     */
    private BigDecimal receptionne = BigDecimal.ZERO;

    /**
     * Le total de reste à réceptionne sur ce budget
     */
    private BigDecimal reste_a_receptionne = BigDecimal.ZERO;
}
