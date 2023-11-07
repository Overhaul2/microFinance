package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUtilisateur extends JpaRepository<Utilisateur, Long> {
    //chercher un utilisateur avec son email et numero de telephone
    Utilisateur findByEmailAndTelephone(String email, int telephone);
    Utilisateur findByEmailAndPassWord(String email, String password);

    Utilisateur findByEmail(String email);

    Utilisateur findByIdUser(Long idUSer);
}
