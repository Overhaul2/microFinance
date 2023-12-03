package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.model.Investisseur;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICreditService {
    Credit AjouterDemande (Credit credit, MultipartFile audioFile) throws Exception;
    Credit ModiffierDemande(Credit credit,long idCredit,MultipartFile audioFile) throws Exception;

    List<Credit> afficherTout();

    Credit lire(Long idCredit);

    Credit Supprimer(Long idCredit) throws Exception;

    List<Credit> getCreditWithNullInvestisseur();

    List<Credit> AfficherCreditParIdInvestisseur(Long idInv);

    //List<Credit> getCreditsByInvestisseurId(Long idInv);
    void ajouterIdInvToCredit(Long idCredit, Long idInv);

    //recherche de credit
    List<Credit> searchCreditByTitre(String titre);

    List<Credit> getCreditsWithNonNullInvestisseurByAgriculteurId(Long idAgr);

}
