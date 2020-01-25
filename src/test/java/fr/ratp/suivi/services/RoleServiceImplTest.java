package fr.ratp.suivi.services;

import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.repositories.RoleRepository;
import fr.ratp.suivi.services.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
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

    /**
     * A mock version of the roleRepository for use in our test
     */
    @Mock
    RoleRepository roleRepository;

    /**
     * The service rolle to test.
     */
    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    @DisplayName("Test find all roles - Success")
    void testFindAllRolesSuccess() {
        //Setup our mock
        Role role = new Role();
        role.setId(ROLE_ID);
        List roles = Arrays.asList(role);
        when(roleRepository.findAllActive()).thenReturn(roles);

        //Execute the service call
        List<Role> allRoles = roleService.findAllActiveRoles();

        //Assert the response
        Assertions.assertNotNull(allRoles, "Rols not found should be 1 object");
        assertThat(allRoles).isNotNull();
        assertThat(allRoles.size()).isEqualTo(1);

        //Verify repository call
        verify(roleRepository).findAllActive();
    }

    @Test
    @DisplayName("Test find role by id - Success")
    void testFindRoleByIdSuccess() {
        //Setup our mock
        Role role = new Role();
        role.setId(ROLE_ID);
        when(roleRepository.findById(ROLE_ID)).thenReturn(Optional.of(role));

        //Execute the service call
        Optional<Role> roleById = roleService.findRoleById(ROLE_ID);

        //Verify repository call
        assertThat(roleById.get()).isNotNull();
        verify(roleRepository).findById(ROLE_ID);
    }

    @Test
    @DisplayName("Test find role by libelle - Success")
    void testFindRoleByLibelleSuccess() {
        //Setup our mock
        Role role = new Role();
        role.setLibelle(ROLE_1);
        when(roleRepository.findByLibelleAndIsActive(ROLE_1, true)).thenReturn(Optional.of(role));

        //Execute the service call
        Optional<Role> result = roleService.findRoleByLibelle(ROLE_1);

        //Verify repository call
        assertThat(result.get()).isNotNull();
        verify(roleRepository).findByLibelleAndIsActive(ROLE_1, true);
    }

}