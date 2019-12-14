package com.ratp.suivi.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * POJO qui répresente une unité local
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
public class LocalUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false,length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean isActive = true;
}
