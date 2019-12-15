package com.ratp.suivi.services;

import com.ratp.suivi.domain.Centre;
import com.ratp.suivi.domain.LocalUnit;
import com.ratp.suivi.repositories.CentreRepository;
import com.ratp.suivi.services.impl.CentreServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CentreServiceImplTest {
    @Mock
    CentreRepository centreRepository;

    @InjectMocks
    CentreServiceImpl centreService;


    @Test
    @DisplayName("Retourner tous les centres ")
    void getAllCentre() {
        Centre centre = new Centre();
        LocalUnit lu = new LocalUnit().builder().code("SDP").build();
        centre.builder().code("01171").id(1L).localUnit(lu).build();
        List lis = Arrays.asList(centre);
        when(centreRepository.findAll()).thenReturn(lis);
        List<Centre> retrievedCentre = centreService.getAllCentre();
        assertThat(retrievedCentre).isNotNull();
        assertThat(retrievedCentre.size()).isEqualTo(1);

        verify(centreRepository).findAll();
    }


    @Test
    @DisplayName("Retourner un centre par identifiant")
    void getCentreById() {
        Centre centre = new Centre();
        centre.setId(1L);
        centre.setCode("01171");

        when(centreRepository.findById(1L)).thenReturn(Optional.of(centre));

        Optional<Centre> centreById = centreService.getCentreById(1L);
        assertThat(centreById.get()).isNotNull();
        assertThat(centreById.get().getId()).isEqualTo(1L);

        verify(centreRepository).findById(1L);
    }
}
