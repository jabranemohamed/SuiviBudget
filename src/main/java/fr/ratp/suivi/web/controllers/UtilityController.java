package fr.ratp.suivi.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.ratp.suivi.domain.Budget;
import fr.ratp.suivi.services.BudgetService;
import fr.ratp.suivi.web.dto.Statistics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "Utility", description = "Point d'entrer pour un utilitaire ")
public class UtilityController extends BaseController {

    @Autowired
    private final BudgetService budgetService;


    @ApiOperation(value = "Retourner toute ")
    @ApiResponses(value = {@ApiResponse(code = 100, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "OK")})

    @GetMapping(produces = {"application/json"}, path = "grandesActivites/{codeUL}/{annee}")
    public ResponseEntity getDistinctGrandeActivite(@PathVariable(value = "annee", required = false) String annee,
                                                    @PathVariable(value = "codeUL", required = false) String localUnit) throws JsonProcessingException {

        List<Budget> allBudgetByUnitCodeYear = budgetService.getAllBudgetByUnitCodeYear(localUnit, annee);
        List<String> pageOfOrigin = allBudgetByUnitCodeYear.stream().map(b -> b.getGrandeActivite()).distinct().collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(pageOfOrigin);
        return new ResponseEntity<>(arrayToJson, HttpStatus.OK);
    }


    @ApiOperation(value = "Retourner tous les budgets paginée qui sont dans l'application")
    @ApiResponses(value = {@ApiResponse(code = 100, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "OK")})

    @GetMapping(produces = {"application/json"}, path = "activites/{grandeActivite}/{codeUL}/{annee}")
    public ResponseEntity getDistinctActivite(@PathVariable(value = "grandeActivite", required = false) String grandeActivite,
                                              @PathVariable(value = "annee", required = false) String annee,
                                              @PathVariable(value = "codeUL", required = false) String localUnit) throws JsonProcessingException {

        List<Budget> allBudgetByUnitCodeYear = budgetService.getAllBudgetByUnitCodeYear(localUnit, annee);
        List<String> pageOfOrigin = allBudgetByUnitCodeYear.stream().filter(b -> b.getGrandeActivite().equals(grandeActivite)).map(a -> a.getActivite()).distinct().collect(Collectors.toList());

        ObjectMapper objectMapper = new ObjectMapper();
        //Set pretty printing of json
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String arrayToJson = objectMapper.writeValueAsString(pageOfOrigin);
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);
    }


    @ApiOperation(value = "Retourner tous les budgets paginée qui sont dans l'application")
    @ApiResponses(value = {@ApiResponse(code = 100, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "OK")})

    @GetMapping(produces = {"application/json"}, path = "statistics/{codeUL}/{annee}")
    public ResponseEntity getStatistics(
            @PathVariable(value = "annee", required = false) String annee,
            @PathVariable(value = "codeUL", required = false) String localUnit) throws JsonProcessingException {

        List<Budget> allBudgets = budgetService.getAllBudgetByUnitCodeYear(localUnit, annee);
        List<Statistics> result = new ArrayList<>();
        //TODO to make it better
        for (AtomicInteger i = new AtomicInteger(0); i.get() < allBudgets.size(); i.incrementAndGet()) {

            Optional<Statistics> first = result
                    .stream()
                    .filter(t -> t.getGrandeActivite().equals(allBudgets.get(i.get()).getGrandeActivite()))
                    .findFirst();

            Budget budget = allBudgets.get(i.get());

            if (first.isPresent()) {
                Statistics statistics = first.get();
                statistics.setBudget_notifie(statistics.getBudget_notifie().add(budget.getBudget_notifie()));
                statistics.setEstime1(statistics.getEstime1().add(budget.getEstime1()));
                statistics.setEstime2(statistics.getEstime2().add(budget.getEstime2()));
                statistics.setEstime3(statistics.getEstime3().add(budget.getEstime3()));
                statistics.setEstime4(statistics.getEstime4().add(budget.getEstime4()));

            } else {
                result.add(new Statistics().builder()
                        .grandeActivite(budget.getGrandeActivite())
                        .budget_notifie(budget.getBudget_notifie())
                        .estime1(budget.getEstime1())
                        .estime2(budget.getEstime2())
                        .estime3(budget.getEstime3())
                        .estime4(budget.getEstime4()).build());
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}


