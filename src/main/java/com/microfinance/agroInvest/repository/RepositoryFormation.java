package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Formation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFormation extends JpaRepository<Formation,Long> {
    
    Formation findByNom(String nom);
    Formation findFormationByIdFor(Long idFor);
}
