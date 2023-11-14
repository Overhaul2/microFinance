package com.microfinance.agroInvest.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.repository.RepositoryCredit;
import com.microfinance.agroInvest.services.CreditServiceImpl;
import com.microfinance.agroInvest.services.ICreditService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/Credit")
@AllArgsConstructor
public class CreditControleur {

    @Autowired
    private CreditServiceImpl creditService;

   @PostMapping("/ajouter")
   // private String AjouterDemanse(@RequestBody Credit credit){
     //   return creditService.AjouterDemande(credit );
   // }
    public ResponseEntity<Credit> ajouter(
          @Valid @RequestParam("credit") String creditString,
           @RequestParam(value = "audio", required = false)MultipartFile audioFile ) throws Exception {
         Credit credit;
       try{
           credit = new JsonMapper().readValue(creditString,Credit.class);

       }catch (Exception e){
           throw new Exception(e.getMessage());
       }
       Credit credit1= creditService.AjouterDemande(credit,audioFile);
       return new ResponseEntity<>(credit1, HttpStatus.CREATED);
   }

    @PutMapping("/modiffier/{idCredit}")
    public ResponseEntity<Credit> modiffier(@PathVariable Long idCredit,
            @RequestParam("credit") String creditString,
            @RequestParam(value = "audio", required = false)MultipartFile audioFile ) throws Exception {
        Credit credit = new Credit();
        try{
            credit = new JsonMapper().readValue(creditString,Credit.class);

        }catch (Exception e){
            throw new Exception("erreur de conversion de la chaîne JSON en objet");
        }
        Credit credit1= creditService.ModiffierDemande(credit,idCredit,audioFile);
        return new ResponseEntity<>(credit1, HttpStatus.OK);
    }

    @GetMapping("/affichertout")
    private List<Credit> affichertout(){
        return creditService.afficherTout();
    }

    @GetMapping("/lire{idCredit}")
    private Credit lire(@RequestParam Long idCredit){
        return creditService.lire(idCredit);
    }

    @DeleteMapping("/supprimer{idCredit}")
    private String supprimer(@RequestParam Long idCredit) throws Exception {
        creditService.Supprimer(idCredit);
        return "supprimer avec succès";
    }
}
