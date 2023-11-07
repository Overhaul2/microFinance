package com.microfinance.agroInvest.controllers;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Utilisateur;
import com.microfinance.agroInvest.services.IUtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("utilisateur")
@AllArgsConstructor
public class UtilisateurControllers {
    private IUtilisateurService utilisateurService;

    @PostMapping("/inscrireagr")
    private String inscrireA(@RequestBody Utilisateur utilisateur){
        return utilisateurService.inscrireAgriculteur(utilisateur);
    }
    @PostMapping("/inscrireinv")
    private String inscrireI(@RequestBody Utilisateur utilisateur) throws NotFoundException {
        return utilisateurService.inscrireInvestisseur(utilisateur);
    }

    @GetMapping("/connexion")
    private String connexion(@RequestParam String email, @RequestParam String password) throws NotFoundException {
        return utilisateurService.connection(email,password);
    }
    @GetMapping("/afficher")
    private List<Utilisateur> afficher(){
        return utilisateurService.afficherTout();
    }
    @GetMapping("/lire")
    private Utilisateur lire(@RequestParam Long idUser){
        return utilisateurService.lire(idUser);
    }
    @PutMapping("/modiffier")
    private String modiffier(@RequestBody Utilisateur utilisateur) throws NotFoundException{
        return utilisateurService.modiffier(utilisateur);
    }
    @DeleteMapping("/supprimer")
    private String supprimer(@RequestParam Long idUser) throws NotFoundException{
        return utilisateurService.supprimer(idUser);
    }
}
