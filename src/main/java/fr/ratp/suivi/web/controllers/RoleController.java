package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Role;
import fr.ratp.suivi.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity getAllRole(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        pageNumber = (pageNumber == null || pageNumber < 0) ? DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;
        Page<Role> pageOfOrigin = roleService.getAllRoles(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);

    }
}
