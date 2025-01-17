package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Budget;
import fr.ratp.suivi.services.BudgetService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "Budget", description = "Point d'entrer pour la ressource budgets")
public class BudgetController extends BaseController {
    @Autowired
    private final BudgetService budgetService;

    @ApiOperation(value = "Retourner tous les budgets paginée qui sont dans l'application")
    @ApiResponses(value = {@ApiResponse(code = 100, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "OK")})

    @GetMapping(produces = {"application/json"}, path = "budgets")
    public ResponseEntity getAllBudgets(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        pageNumber = (pageNumber == null || pageNumber < 0) ? DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;
        Page<Budget> pageOfOrigin = budgetService.getAllBudgets(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);
    }

    @ApiOperation(value = "Retourner les budgets pour une année et une unité spécifique")
    @GetMapping(produces = {"application/json"}, path = "budgets/{codeUL}/{annee}")
    public ResponseEntity getBudgetsByYearAndUnit(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                  @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                  @PathVariable(value = "codeUL", required = true) String codeUL,
                                                  @PathVariable(value = "annee", required = true) String annee) {

        pageNumber = (pageNumber == null || pageNumber < 0) ? DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;
        Page<Budget> pageOfOrigin = budgetService.getAllBudgetByUnitCodeYear(codeUL, annee, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);
    }

    @ApiOperation(value = "Retourner les infos d'un budget identifié par une année , codeUnit,grandActivité,activité")
    @GetMapping(produces = {"application/json"}, path = "budget-infos/{codeUL}/{annee}/{grandActivite}/{activite}")
    public ResponseEntity getBudgetsByYearAndUnit(@PathVariable(value = "codeUL", required = true) String codeUL,
                                                  @PathVariable(value = "annee", required = true) String annee,
                                                  @PathVariable(value = "grandActivite", required = true) String grandActivite,
                                                  @PathVariable(value = "activite", required = true) String activite) {
        List<Budget> budgetInfos = budgetService.getBudgetInfos(codeUL, annee, grandActivite, activite);
        return new ResponseEntity<>(budgetInfos.get(0), HttpStatus.OK);
    }


}
