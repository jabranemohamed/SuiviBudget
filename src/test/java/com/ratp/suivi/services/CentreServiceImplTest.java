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
    void getAllLocalUnit() {
        Centre centre = new Centre();
        LocalUnit lu = new LocalUnit().builder().code("SDP").build();
        centre.builder().code("01171").localUnit(lu).build();

        List lis = Arrays.asList(centre);

        when(centreRepository.findAll()).thenReturn(lis);
        List<Centre> retrievedCentre = centreService.getAllCentre();
        assertThat(retrievedCentre).isNotNull();
        assertThat(retrievedCentre.size()).isEqualTo(1);

        verify(centreRepository).findAll();
    }

}
