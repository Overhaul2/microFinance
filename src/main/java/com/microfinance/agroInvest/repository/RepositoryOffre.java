package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepositoryOffre extends JpaRepository<Offre , Long> {

    Offre findByTitre(String Nom);
    Offre findByIdOf(Long idCredit);

    List<Offre> findByInvestisseurIdInv(Long idInv);
}
