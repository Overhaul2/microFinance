package com.microfinance.agroInvest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.services.AgriculteurServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/agriculteur")
@AllArgsConstructor
public class AgriculteurController {
    private AgriculteurServiceImpl agriculteurService;
    @PostMapping("/inscrire")
    public ResponseEntity<Agriculteur> inscrire(
            @Valid @RequestParam("agriculteur") String agriculteurString,
            @RequestParam(value = "image", required = false)MultipartFile imageFile ) throws Exception {
        Agriculteur agriculteur = new Agriculteur();
        try{
            agriculteur = new JsonMapper().readValue(agriculteurString,Agriculteur.class);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        Agriculteur agriculteur1= agriculteurService.inscrire(agriculteur,imageFile);
        System.out.println("test agriculteur"+agriculteur1);
        return new ResponseEntity<>(agriculteur1, HttpStatus.CREATED);
    }

    @PutMapping("/modiffier/{idAgr}")
    public ResponseEntity<?> modifer(@PathVariable Long idAgr,
                                          @RequestParam("agriculteur") String agriculteurString,
                                          @RequestParam(value = "image", required = false)MultipartFile multipartFile ) throws Exception {
        Agriculteur agriculteur = new Agriculteur();
        try{
            agriculteur = new JsonMapper().readValue(agriculteurString,Agriculteur.class);

        }catch (JsonProcessingException e){
            throw new Exception(e.getMessage());
        }
        Agriculteur agriculteur1= agriculteurService.modiffier(agriculteur,idAgr,multipartFile);
        return new ResponseEntity<>(agriculteur1, HttpStatus.OK);
    }
    @DeleteMapping("/supprimer{idAgr}")
    private String supprimer(@RequestParam long idAgr)  {
        agriculteurService.supprimer(idAgr);
        return "supprimer avec succès";

    }
    @GetMapping("/lire{idAgr}")
    private Agriculteur lire(@RequestParam long idAgr){ return agriculteurService.lire(idAgr);
    }
    @GetMapping("/affichertout")
    private List<Agriculteur> afficherTout(){ return agriculteurService.affichertout();
    }
    @GetMapping("/connexion")
    private Agriculteur connexion(@RequestParam("email")String email,@RequestParam("password")String password) throws Exception {
        return agriculteurService.connexion(email, password);

    }
}
