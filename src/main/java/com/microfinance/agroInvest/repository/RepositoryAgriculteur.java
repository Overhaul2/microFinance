package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Utilisateur;

public interface RepositoryAgriculteur {

    //chercher un utilisateur avec son email et numero de telephone
    Utilisateur findByEmailAndTelephone(String email, int telephone);
    Utilisateur findByEmailAndPassWord(String email, String password);

    Utilisateur findByEmail(String email);

    Utilisateur findByIdUser(Long idUser);
}
