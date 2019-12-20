package fr.ratp.suivi.domain;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * POJO qui répresene le role d'un utilisateur dans l'application
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
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Transient
    @Override
    public String getAuthority() {
        return libelle;
    }
}
