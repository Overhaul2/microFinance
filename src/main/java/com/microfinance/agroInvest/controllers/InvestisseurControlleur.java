package com.microfinance.agroInvest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Investisseur;
import com.microfinance.agroInvest.services.InvestisseurServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("investisseur")
@AllArgsConstructor
public class InvestisseurControlleur {


    @Autowired
    private InvestisseurServiceImpl investisseurService;
    @PostMapping("/inscrire")
    public ResponseEntity<Investisseur> inscrire(
            @Valid @RequestParam("investisseur") String investisseurString,
            @RequestParam(value = "image", required = false) MultipartFile imageFile ) throws Exception {
        Investisseur investisseur = new Investisseur();
        try{
            investisseur = new JsonMapper().readValue(investisseurString,Investisseur.class);

        }catch (Exception e){
            throw new NotFoundException("impossible d'ajouter");
        }
        Investisseur investisseur1= investisseurService.inscrire(investisseur,imageFile);
        return new ResponseEntity<>(investisseur1, HttpStatus.CREATED);
    }

    @PutMapping("/modiffier/{idInv}")
    public ResponseEntity<?> modiffer(@PathVariable Long idInv,
                                     @RequestParam("investisseur") String investisseurString,
                                     @RequestParam(value = "image", required = false)MultipartFile multipartFile ) throws Exception {
        Investisseur investisseur = new Investisseur();
        try{
            investisseur = new JsonMapper().readValue(investisseurString,Investisseur.class);

        }catch (JsonProcessingException e){
            throw new Exception(e.getMessage());
        }
        Investisseur investisseur1= investisseurService.modiffier(investisseur,idInv,multipartFile);
        return new ResponseEntity<>(investisseur1, HttpStatus.OK);
    }
    @DeleteMapping("/supprimer{idInv}")
    private String supprimer(@RequestParam long idInv) throws Exception {
        investisseurService.supprimer(idInv);
        return "supprimer avec succès";

    }
    @GetMapping("/lire{idInv}")
    private Investisseur lire(@RequestParam long idInv){ return investisseurService.lire(idInv);
    };
    @GetMapping("/afficherTout")
    private List<Investisseur> afficherTout(){ return investisseurService.affichertout();
    };
    @PostMapping("/connexion")
    public Object connexion(@RequestParam("email")String email,@RequestParam("password")String password){
        return investisseurService.connexion(email, password);

    }
    }

