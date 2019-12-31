package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Commande;
import fr.ratp.suivi.services.CommandeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "Commande", description = "Point d'entrer pour la ressource commands")
public class CommandController extends BaseController{

    @Autowired
    private CommandeService commandeService;


    @ApiOperation(value = "Retourner les commandes pour une année et une unité spécifique")
    @GetMapping(produces = {"application/json"}, path = "commandes/{codeUL}/{annee}")
    public ResponseEntity getBudgetsByYearAndUnit(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                  @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                  @PathVariable(value = "codeUL", required = true) String codeUL,
                                                  @PathVariable(value = "annee", required = true) String annee) {

        pageNumber = (pageNumber == null || pageNumber < 0) ? DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;
        List<Commande> pageOfOrigin = commandeService.getAllCommandByUnitCodeYear(codeUL,annee);
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);
    }

}
