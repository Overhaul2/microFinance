package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.model.Investisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryCredit extends JpaRepository<Credit , Long> {
    Credit findByTitre(String Nom);

    Credit findByIdCredit(Long idCredit);

    List<Credit> findByAgriculteurIdAgr(Long idAgr);

    List<Credit> findByOffreInvestisseurIdInv(Long idInv);


}
