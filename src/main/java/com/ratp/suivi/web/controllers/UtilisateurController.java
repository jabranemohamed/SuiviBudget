package com.ratp.suivi.web.controllers;

import com.ratp.suivi.domain.Utilisateur;
import com.ratp.suivi.jwt.JwtTokenProvider;
import com.ratp.suivi.services.UtilisateurService;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public ResponseEntity getAllUtilisateurs(@PathVariable(value = "codeUL", required = true) String matricule) {

        Optional<Utilisateur> userByMatricule = utilisateurService.getUserByMatricule(matricule);
        if (userByMatricule.isPresent())
            return new ResponseEntity<>(userByMatricule.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ApiOperation(value = "Login à l'application")
    @ApiResponses(value = {@ApiResponse(code = 100, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 200, message = "OK")})
    @GetMapping("utilisateurs/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal == null){
            //This should be ok http status because here will be logout path.
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        Utilisateur user = utilisateurService.getUserById(1L).get();//TODO to change
        user.setToken(tokenProvider.generateToken(authenticationToken));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
