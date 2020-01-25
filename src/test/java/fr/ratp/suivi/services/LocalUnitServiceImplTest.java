package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.LocalUnit;
import fr.ratp.suivi.repositories.LocalUnitRepository;
import fr.ratp.suivi.services.impl.LocalUnitServiceImpl;
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
public class LocalUnitServiceImplTest {

    @Mock
    LocalUnitRepository localUnitRepository;

    @InjectMocks
    LocalUnitServiceImpl localUnitService;

    @Test
    @DisplayName("Retourner tous les unités local")
    void getAllLocalUnit() {
        //Setup our mock
        LocalUnit lu = new LocalUnit().builder().code("SDP").build();
        List lis = Arrays.asList(lu);
        when(localUnitRepository.findAll()).thenReturn(lis);

        //Execute the service call
        List<LocalUnit> retrievedLocalUnit = localUnitService.findAllLocalUnit();

        //Verify repository call
        assertThat(retrievedLocalUnit).isNotNull();
        assertThat(retrievedLocalUnit.size()).isEqualTo(1);
        verify(localUnitRepository).findAll();
    }
}