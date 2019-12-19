package fr.ratp.suivi.web.controllers;

import fr.ratp.suivi.domain.Utilisateur;
import fr.ratp.suivi.exception.GlobalExceptionHandlerController;
import fr.ratp.suivi.jwt.JwtTokenProvider;
import fr.ratp.suivi.services.UtilisateurService;
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

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
@Api(value = "Utilisateur", description = "Point d'entrer pour la ressource utilisateur ")
public class UtilisateurController extends BaseController {

    @Autowired
    private final UtilisateurService utilisateurService;

    @Autowired
    private final JwtTokenProvider tokenProvider;

    @ApiOperation(value = "Retourner tous les utilisateurs qui sont dans l'application")
    @ApiResponses(value = {@ApiResponse(code = 100, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping(produces = {"application/json"}, path = "utilisateurs")
    public ResponseEntity getAllUtilisateurs(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        pageNumber = (pageNumber == null || pageNumber < 0) ? DEFAULT_PAGE_NUMBER : pageNumber;
        pageSize = (pageSize == null || pageSize < 1) ? DEFAULT_PAGE_SIZE : pageSize;
        Page<Utilisateur> pageOfOrigin = utilisateurService.getAllUser(PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(pageOfOrigin, HttpStatus.OK);
    }


    @ApiOperation(value = "Retourner un utilisateur identifié par son matricule")
    @ApiResponses(value = {@ApiResponse(code = 100, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping(produces = {"application/json"}, path = "utilisateurs/{matricule}")
    public ResponseEntity getAllUtilisateurs(@PathVariable(value = "matricule", required = true) String matricule) {

        Optional<Utilisateur> userByMatricule = utilisateurService.getUserByMatricule(matricule);
        if (userByMatricule.isPresent())
            return new ResponseEntity<>(userByMatricule.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @ApiOperation(value = "Register a new User")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 422, message = "Username is already in use"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    @PostMapping(value = "utilisateurs/signup")
    @ExceptionHandler(GlobalExceptionHandlerController.class)
    public ResponseEntity signup(@RequestBody Utilisateur user) {
        Utilisateur signedUser = utilisateurService.signup(user);
        return new ResponseEntity<>(signedUser, HttpStatus.OK);
    }


    @ApiOperation(value = "Login à l'application")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    @PostMapping(value = "utilisateurs/login")
    @ExceptionHandler(GlobalExceptionHandlerController.class)
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        Utilisateur user = utilisateurService.signin(username, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
