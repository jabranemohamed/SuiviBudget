package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Centre;
import fr.ratp.suivi.domain.LocalUnit;
import fr.ratp.suivi.repositories.CentreRepository;
import fr.ratp.suivi.services.impl.CentreServiceImpl;
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
        centre.builder().code("01171").localUnit(lu).build();
        List lis = Arrays.asList(centre);
        when(centreRepository.findAll()).thenReturn(lis);
        List<Centre> retrievedCentre = centreService.getAllActiveCentre();
        assertThat(retrievedCentre).isNotNull();
        assertThat(retrievedCentre.size()).isEqualTo(1);

        verify(centreRepository).findAll();
    }


    @Test
    @DisplayName("Retourner un centre par identifiant")
    void getCentreByCode() {
        Centre centre = new Centre();
        centre.setCode("01171");

        when(centreRepository.findByCode("01171")).thenReturn(Optional.of(centre));

        Optional<Centre> centreByCode = centreService.getCentreByCode("01171");
        assertThat(centreByCode.get()).isNotNull();

        verify(centreRepository).findByCode("01171");
    }
}
