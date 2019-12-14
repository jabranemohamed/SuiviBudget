package com.ratp.suivi.web.controllers;

import com.ratp.suivi.domain.Budget;
import com.ratp.suivi.services.BudgetService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        Page<Budget> pageOfOrigin = budgetService.getAllBudgetsByYearAndUnit(codeUL, annee, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);
    }


}
