package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.repository.RepositoryAgriculteur;

import java.util.List;

public class AgriculteurServiceImpl implements IAgriculteurService {
    private RepositoryAgriculteur repositoryAgriculteur;
    @Override
    public String inscrire(Agriculteur agriculteur) {
        Agriculteur agriculteur1 = (Agriculteur) repositoryAgriculteur.findByEmailAndTelephone(agriculteur.getEmail(), agriculteur.getTelephone());
        if (agriculteur1==null){

        }
        return null;
    }

    @Override
    public String modiffier(Agriculteur agriculteur) {
        return null;
    }

    @Override
    public String connexion(String email, String password) {
        return null;
    }

    @Override
    public List<Agriculteur> lire(Long idUser) {
        return null;
    }

    @Override
    public List<Agriculteur> affichertout() {
        return null;
    }

    @Override
    public String supprimer(Long idUser) {
        return null;
    }
}
