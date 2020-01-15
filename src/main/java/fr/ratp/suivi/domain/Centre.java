package fr.ratp.suivi.domain;

import lombok.*;

import javax.persistence.*;

/**
 * POJO qui represente un centre d'unité local
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
public class Centre {
    @Id
    @Column(unique = true, nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "localUnit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_LocalUnit"))
    private LocalUnit localUnit;

    @Column(nullable = false)
    private Boolean isActive = true;
}
