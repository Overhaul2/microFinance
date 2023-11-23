package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryOffre extends JpaRepository<Offre , Long> {

    Offre findByTitre(String Nom);
    Offre findByIdOf(Long idCredit);
}
