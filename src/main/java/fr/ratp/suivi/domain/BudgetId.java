package fr.ratp.suivi.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class BudgetId implements Serializable {
    private String grandeActivite;

    private String activite;

}