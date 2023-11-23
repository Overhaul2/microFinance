package com.microfinance.agroInvest.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.model.Offre;
import com.microfinance.agroInvest.services.OffreServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/offre")
@AllArgsConstructor
public class OffreController {
     @Autowired
    private OffreServiceImpl offreService;

     @PostMapping("/ajouter")
    public ResponseEntity<Offre> ajouterOffre(
             @Valid @RequestParam("offre") String offreString,
             @RequestParam(value = "audio", required = false) MultipartFile audioFile) throws Exception {
    Offre offre;
        try{
            offre = new JsonMapper().readValue(offreString, Offre.class);

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        Offre offre1= offreService.AjouterOffre(offre,audioFile);
        return new ResponseEntity<>(offre1, HttpStatus.CREATED);
    }

}
