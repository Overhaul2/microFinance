package com.microfinance.agroInvest.controllers;

import com.microfinance.agroInvest.model.DataDTO;
import com.microfinance.agroInvest.services.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/CreditsOffres")
public class DataController {
private DataService dataService;
    @GetMapping("/HistoriqueAgr/{idAgr}")
    public List<DataDTO> getDataByAgriculteur(@PathVariable Long idAgr) {
        return dataService.getAllDataByAgriculteur(idAgr);
    }
    @GetMapping("/HistoriqueInvestisseur/{idInv}")
    public List<DataDTO> getDataByInvestiseur(@PathVariable Long idInv) {
        return dataService.getAllDataByInvestisseur(idInv);
    }


}
