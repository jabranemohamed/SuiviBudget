package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.LocalUnit;
import fr.ratp.suivi.services.LocalUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "LocalUnit", description = "Point d'entrer pour la ressource LocalUnit ")
public class LocalUnitController extends BaseController {
    @Autowired
    private final LocalUnitService localUnitService;

    @ApiOperation(value = "Retourner tous les roles qui sont dans l'application")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 100, message = "Bad Request"),
                    @ApiResponse(code = 404, message = "Not Found"),
                    @ApiResponse(code = 200, message = "OK")
            }
    )
    @GetMapping(produces = {"application/json"}, path = "localUnites")
    public ResponseEntity getAllCentre() {

        List<LocalUnit> pageOfOrigin = localUnitService.findAllLocalUnit();
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);

    }
}
