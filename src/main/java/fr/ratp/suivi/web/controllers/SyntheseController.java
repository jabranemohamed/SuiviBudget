package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Synthese;
import fr.ratp.suivi.services.SyntheseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "Synthese", description = "Point d'entrer pour la ressource synthese")
public class SyntheseController {


    @Autowired
    private final SyntheseService syntheseService;


    @ApiOperation(value = "Retourner les syntheses des budgets pour une année et une unité spécifique")
    @GetMapping(produces = {"application/json"}, path = "synthese/{codeUL}/{annee}")
    public ResponseEntity getBudgetsByYearAndUnit(@PathVariable(value = "codeUL", required = true) String codeUL,
                                                  @PathVariable(value = "annee", required = true) String annee) {

        List<Synthese> listSynthese = syntheseService.findListOfSynthese(codeUL, annee);
        return new ResponseEntity<>(listSynthese, HttpStatus.OK);
    }


}
