package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Utilisateur;

import java.util.List;

public interface IUtilisateurService  {
    //inscription
    String inscrireAgriculteur(Utilisateur utilisateur);
    String inscrireInvestisseur(Utilisateur utilisateur) throws NotFoundException;
    //connection
    String connection(String Email, String passWord) throws NotFoundException;

    //Afficher tout les utilisateur
    List<Utilisateur> afficherTout();

    //Afficher un utilisateur par id
    Utilisateur lire(Long idUser);

    //modiffier utilisateur
    String modiffier(Utilisateur utilisateur) throws NotFoundException;

    //supprimer un user
    String supprimer(Long idUser) throws NotFoundException;

}
