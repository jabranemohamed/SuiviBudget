package fr.ratp.suivi.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.ratp.suivi.domain.Synthese;
import fr.ratp.suivi.services.SyntheseService;
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


    @ApiOperation(value = "Retourner tous les budgets paginée qui sont dans l'application")
    @ApiResponses(value = {@ApiResponse(code = 100, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "OK")})

    @GetMapping(produces = {"application/json"}, path = "statistics/{codeUL}/{annee}")
    public ResponseEntity getStatistics(
            @PathVariable(value = "annee", required = false) String annee,
            @PathVariable(value = "codeUL", required = false) String localUnit) throws JsonProcessingException {

        List<Synthese> listSynthese = syntheseService.findListOfSynthese(localUnit, annee);
        List<Statistics> result = new ArrayList<>();
        //TODO to make it better

        for (AtomicInteger i = new AtomicInteger(0); i.get() < listSynthese.size(); i.incrementAndGet()) {

            Optional<Statistics> first = result
                    .stream()
                    .filter(t -> t.getGrandeActivite().equals(listSynthese.get(i.get()).getBudget().getBudgetId().getGrandeActivite()))
                    .findFirst();

            Synthese synthese = listSynthese.get(i.get());

            if (first.isPresent()) {
                Statistics statistics = first.get();
                statistics.setGrandeActivite(synthese.getBudget().getBudgetId().getGrandeActivite());
                statistics.setEngage(statistics.getEngage().add(synthese.getEngage()));
                statistics.setReceptionne(statistics.getReceptionne().add(synthese.getReceptionne()));
                statistics.setReste_a_receptionne(statistics.getReste_a_receptionne().add(synthese.getReste_a_receptionne()));

            } else {
                result.add(new Statistics().builder()
                        .grandeActivite(synthese.getBudget().getBudgetId().getGrandeActivite())
                        .engage(synthese.getEngage())
                        .receptionne(synthese.getReceptionne())
                        .reste_a_receptionne(synthese.getReste_a_receptionne()).build());
            }
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
