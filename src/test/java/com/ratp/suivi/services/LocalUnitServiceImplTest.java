package com.ratp.suivi.services;

import com.ratp.suivi.domain.LocalUnit;
import com.ratp.suivi.repositories.LocalUnitRepository;
import com.ratp.suivi.services.impl.LocalUnitServiceImpl;
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
        LocalUnit lu = new LocalUnit().builder().code("SDP").build();
        List lis = Arrays.asList(lu);

        when(localUnitRepository.findAll()).thenReturn(lis);
        List<LocalUnit> retrievedLocalUnit = localUnitService.getAllLocalUnit();
        assertThat(retrievedLocalUnit).isNotNull();
        assertThat(retrievedLocalUnit.size()).isEqualTo(1);

        verify(localUnitRepository).findAll();
    }
}