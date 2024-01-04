package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Investisseur;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IInvestisseurService {

    Investisseur inscrire(Investisseur investisseur, MultipartFile imageFile) throws Exception;

    Investisseur modiffier(Investisseur investisseur, Long idInv, MultipartFile imageFile) throws Exception;

    Investisseur connexion(String email, String password) throws Exception;

    Investisseur lire(Long idInv);

    List<Investisseur> affichertout();

    Investisseur supprimer(Long idAgr) throws NotFoundException;
}
