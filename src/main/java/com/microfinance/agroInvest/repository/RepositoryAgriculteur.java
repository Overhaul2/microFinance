package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAgriculteur extends JpaRepository<Agriculteur , Long> {

    //chercher un utilisateur avec son email et numero de telephone
    Agriculteur findByEmailAndTelephone(String email, int telephone);
    Agriculteur findByEmailAndPassWord(String email, String password);

    Agriculteur findByEmail(String email);

    Agriculteur findByIdAgr(long idAgr);
}
