package com.microfinance.agroInvest.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.repository.RepositoryAgriculteur;
import com.microfinance.agroInvest.services.AgriculteurServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.util.List;

@RestController
@RequestMapping("/agriculteur")
@AllArgsConstructor
public class AgriculteurController {
    @Autowired
    private AgriculteurServiceImpl agriculteurService;
    @PostMapping("/inscrire")
    public ResponseEntity<Agriculteur> inscrire(
            @Valid @RequestParam("agriculteur") String agriculteurString,
            @RequestParam(value = "image", required = false)MultipartFile imageFile ) throws Exception {
        Agriculteur agriculteur = new Agriculteur();
        try{
            agriculteur = new JsonMapper().readValue(agriculteurString,Agriculteur.class);

        }catch (Exception e){
            throw new NotFoundException("impossible d'ajouter");
        }
        Agriculteur agriculteur1= agriculteurService.inscrire(agriculteur,imageFile);
        return new ResponseEntity<>(agriculteur1, HttpStatus.CREATED);
    }

    @PutMapping("/modiffier{idAgr}")
    public ResponseEntity<Agriculteur> Modifer(@PathVariable long idAgr,
                                          @RequestParam("agriculteur") String agriculteurSring,
                                          @RequestParam(value = "DescriptionAudio", required = false)MultipartFile imageFile ) throws Exception {
        Agriculteur agriculteur = new Agriculteur();
        try{
            agriculteur = new JsonMapper().readValue((DataInput) agriculteur,Agriculteur.class);

        }catch (Exception e){
            throw new NotFoundException("impossible d'ajouter");
        }
        Agriculteur agriculteur1= agriculteurService.modiffier(agriculteur,idAgr,imageFile);
        return new ResponseEntity<>(agriculteur1, HttpStatus.OK);
    }
    @DeleteMapping("/supprimer{idAgr}")
    private String supprimer(@RequestParam long idAgr) throws Exception {
        agriculteurService.supprimer(idAgr);
        return "supprimer avec succès";

    }
    @GetMapping("/lire{idAgr}")
    private Agriculteur lire(@RequestParam long idAgr){ return agriculteurService.lire(idAgr);
    };
    @GetMapping("/afficherTout")
    private List<Agriculteur> afficherTout(){ return agriculteurService.affichertout();
    };
}
