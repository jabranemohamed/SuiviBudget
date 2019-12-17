package fr.ratp.suivi.domain;

import lombok.*;

import javax.persistence.*;

/**
 * POJO qui represente un utilisateur de l'application
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
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String matricule;

    private String nom;

    private String prenom;

    @Column(nullable = false)
    private Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ROLE"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "localUnit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_LocalUnit"))
    private LocalUnit localUnit;

    @Transient
    private String token;

}
