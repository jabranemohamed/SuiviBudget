package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.services.impl.RoleServiceImpl;
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
public class RoleServiceImplTest {

    public static final String ROLE_1 = "role1";
    public static final long ROLE_ID = 1L;
    @Mock
    RoleRepository roleRepository;

    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    @DisplayName("Retourner tous les roles")
    void getAllRoles() {
        Role role = new Role();
        role.setId(ROLE_ID);
        List roles = Arrays.asList(role);
        when(roleRepository.findAll()).thenReturn(roles);

        List<Role> allRoles = roleService.getAllRoles();
        assertThat(allRoles).isNotNull();
        assertThat(allRoles.size()).isEqualTo(1);

        verify(roleRepository).findAll();
    }

    @Test
    @DisplayName("Retourner un role par identifiant")
    void getRoleById() {
        Role role = new Role();
        role.setId(ROLE_ID);
        when(roleRepository.findById(ROLE_ID)).thenReturn(Optional.of(role));

        Optional<Role> roleById = roleService.getRoleById(ROLE_ID);
        assertThat(roleById.get()).isNotNull();

        verify(roleRepository).findById(ROLE_ID);
    }

    @Test
    @DisplayName("Retourner tous les roles")
    void getRoleByLibelle() {
        Role role = new Role();
        role.setLibelle(ROLE_1);
        when(roleRepository.findByLibelle(ROLE_1)).thenReturn(Optional.of(role));

        Optional<Role> result = roleService.getRoleByLibelle(ROLE_1);
        assertThat(result.get()).isNotNull();

        verify(roleRepository).findByLibelle(ROLE_1);
    }

}