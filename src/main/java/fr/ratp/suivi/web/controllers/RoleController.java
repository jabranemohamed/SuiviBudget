package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "Role", description = "Point d'entrer pour la ressource role ")
public class RoleController extends BaseController {

    @Autowired
    private final RoleService roleService;

    @ApiOperation(value = "Retourner tous les roles qui sont dans l'application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "OK")
            }
    )
    @GetMapping(produces = {"application/json"}, path = "roles")
    public ResponseEntity getAllRole() {
        List<Role> pageOfOrigin = roleService.findAllActiveRoles();
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);

    }

    @ApiOperation(value = "Mettre à jour un Role")
    @PutMapping(path = "role")
    public ResponseEntity updateRole(@RequestBody Role role) {
        Optional<Role> existingRole = roleService.findRoleByLibelle(role.getLibelle());
        return existingRole.map(p -> {
            Role updatedRole = roleService.updateRole(role);
            return ResponseEntity.ok().body(updatedRole);
        }).orElse(ResponseEntity.notFound().build());

    }

}
