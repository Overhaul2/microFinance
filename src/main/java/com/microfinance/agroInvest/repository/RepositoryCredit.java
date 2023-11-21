package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCredit extends JpaRepository<Credit , Long> {
    Credit findByTitre(String Nom);
    Credit findByIdCredit(Long idCredit);
}
