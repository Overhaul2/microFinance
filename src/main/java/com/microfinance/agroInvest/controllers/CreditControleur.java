package com.microfinance.agroInvest.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.services.CreditServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@CrossOrigin(origins = "*")
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
           @RequestParam(value = "audio", required = false)MultipartFile audioFile) throws Exception {
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
        //System.out.println("mes credits========="+creditService.afficherTout().size());
       return creditService.afficherTout();
    }

    @GetMapping("/list/{idAgr}")
    public ResponseEntity<List<Credit>> listeCredit(@PathVariable Long idAgr){
        return  new ResponseEntity<>(creditService.lireParAgriculteur(idAgr), HttpStatus.OK);
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

    @GetMapping("/creditsansinvestisseur")
    public List<Credit> CreditSansInvestisseur(){
       return creditService.getCreditWithNullInvestisseur();
    }

    @GetMapping("/afficherparidInv/{idInv}")
    public  List<Credit> afficherCreditparIdInv(@PathVariable Long idInv){
       return creditService.AfficherCreditParIdInvestisseur(idInv);
    }

    @PutMapping("/accepterDemande/{idCredit}/{idInv}")
    public void accepterDemande(@PathVariable Long idCredit, @PathVariable Long idInv){
       creditService.ajouterIdInvToCredit(idCredit,idInv);
    }

    @GetMapping("/recherche/{titre}")
    public  List<Credit> rechercherParTitre(@PathVariable String titre){
       return creditService.searchCreditByTitre(titre);
    }

    @GetMapping("/investisseur/{idAgr}")
    public ResponseEntity<List<Credit>> getCreditsWithNonNullInvestisseurByAgriculteurId(@PathVariable Long idAgr) {
        List<Credit> credits = creditService.getCreditsWithNonNullInvestisseurByAgriculteurId(idAgr);
        return ResponseEntity.ok(credits);
    }


}
