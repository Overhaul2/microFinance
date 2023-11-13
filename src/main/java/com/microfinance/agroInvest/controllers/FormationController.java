package com.microfinance.agroInvest.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Formation;
import com.microfinance.agroInvest.services.FormationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationController {
    private FormationServiceImpl formationService;

    @PostMapping("/Ajouter")
    public ResponseEntity<Formation> AjouterFormation(
            @Valid @RequestParam("formation") String formationtString,
            @RequestParam(value = "video", required = false) MultipartFile videFile ) throws Exception {
        Formation formation = new Formation();
        try{
            formation = new JsonMapper().readValue(formationtString,Formation.class);

        }catch (Exception e){
            throw new NotFoundException("impossible d'ajouter");
        }
        Formation formation1= formationService.ajouter(formation,videFile);
        return new ResponseEntity<>(formation1, HttpStatus.CREATED);
    }

    @PutMapping("/Modiffier/{idFor}")
    public ResponseEntity<Formation> Modifer(@PathVariable Long idFor,
                                          @RequestParam("formation") String formationString,
                                          @RequestParam(value = "video", required = false)MultipartFile videoFile ) throws Exception {
        Formation formation = new Formation();
        try{
            formation = new JsonMapper().readValue(formationString,Formation.class);

        }catch (Exception e){
            throw new NotFoundException("impossible d'ajouter");
        }
        Formation formation1= formationService.modiffier(formation,idFor,videoFile);
        return new ResponseEntity<>(formation1, HttpStatus.OK);
    }

    @GetMapping("/afficherTout")
    private List<Formation> afficherTout(){
        return formationService.affichertout();
    }

    @GetMapping("/lire{idFor}")
    private Formation lire(@RequestParam Long idFor){
        return formationService.lire(idFor);
    }

    @DeleteMapping("/supprimer{idFor}")
    private String supprimer(@RequestParam Long idFor) throws Exception {
        formationService.supprimer(idFor);
        return "supprimer avec succès";
    }
}
