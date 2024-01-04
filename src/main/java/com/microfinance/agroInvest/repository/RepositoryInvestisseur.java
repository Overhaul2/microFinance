package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Investisseur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryInvestisseur extends JpaRepository<Investisseur,Long> {


    //chercher un utilisateur avec son email et numero de telephone
    Investisseur findByEmailAndTelephone(String email, int telephone);
    Investisseur findByEmailAndPassWord(String email, String password);

    Investisseur findByEmail(String email);

    Investisseur findByIdInv(long idAgr);
}
