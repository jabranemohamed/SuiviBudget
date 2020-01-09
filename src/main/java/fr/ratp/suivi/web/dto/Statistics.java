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

    private BigDecimal budget_notifie = BigDecimal.ZERO;

    private BigDecimal estime1 = BigDecimal.ZERO;

    private BigDecimal estime2 = BigDecimal.ZERO;

    private BigDecimal estime3 = BigDecimal.ZERO;

    private BigDecimal estime4 = BigDecimal.ZERO;
}
