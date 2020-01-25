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
    @DisplayName("Find all actives centre - Success")
    void testFindAllCentreSuccess() {
        //Setup our mock
        Centre centre = new Centre();
        LocalUnit lu = new LocalUnit().builder().code("SDP").build();
        centre.builder().code("01171").localUnit(lu).isActive(true).build();
        List lis = Arrays.asList(centre);
        when(centreRepository.findAllActive()).thenReturn(lis);

        //Execute the service call
        List<Centre> retrievedCentre = centreService.findAllActiveCentre();

        //Assert the response
        assertThat(retrievedCentre).isNotNull();
        assertThat(retrievedCentre.size()).isEqualTo(1);
        verify(centreRepository).findAllActive();
    }


    @Test
    @DisplayName("Find a centre by his code - Success")
    void testFindCentreByCodeSuccess() {
        //Setup our mock
        Centre centre = new Centre();
        centre.setCode("01171");
        when(centreRepository.findByCode("01171")).thenReturn(Optional.of(centre));

        //Execute the service call
        Optional<Centre> centreByCode = centreService.findCentreByCode("01171");

        //Assert the response
        assertThat(centreByCode.get()).isNotNull();
        verify(centreRepository).findByCode("01171");
    }
}
