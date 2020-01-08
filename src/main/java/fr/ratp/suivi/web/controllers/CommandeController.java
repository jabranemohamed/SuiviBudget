package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Commande;
import fr.ratp.suivi.services.CommandeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "Commande", description = "Point d'entrer pour la ressource commandes")
public class CommandeController extends BaseController {

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
        Page<Commande> pageOfCommande = commandeService.getAllCommandByUnitCodeYear(codeUL, annee, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(pageOfCommande, HttpStatus.OK);
    }


    @ApiOperation(value = "Mettre à jour les commandes")
    @PutMapping(path = "commandes")
    public ResponseEntity getBudgetsByYearAndUnit(@RequestBody List<Commande> commandes) {
        Page<Commande> pageOfCommande = null;
        return new ResponseEntity<>(pageOfCommande, HttpStatus.OK);
    }

}
