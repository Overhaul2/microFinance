package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Agriculteur;

import java.util.List;

public interface IAgriculteurService {

    String inscrire(Agriculteur agriculteur);

    String modiffier(Agriculteur agriculteur);

    String connexion(String email, String password);

    List<Agriculteur> lire(Long idUser);

    List<Agriculteur> affichertout();

    String supprimer(Long idUser);
}
