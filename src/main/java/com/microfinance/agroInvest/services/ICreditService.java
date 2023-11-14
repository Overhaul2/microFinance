package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Credit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICreditService {
    Credit AjouterDemande (Credit credit, MultipartFile audioFile) throws Exception;
    Credit ModiffierDemande(Credit credit,long idCredit,MultipartFile audioFile) throws Exception;

    List<Credit> afficherTout();

    Credit lire(Long idCredit);

    Credit Supprimer(Long idCredit) throws Exception;
}
