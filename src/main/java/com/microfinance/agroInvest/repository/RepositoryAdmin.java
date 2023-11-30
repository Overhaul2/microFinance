package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Admin;
import com.microfinance.agroInvest.model.Agriculteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAdmin extends JpaRepository<Admin , Long> {

    Admin findByEmailAndPassword(String email, String password);
    Admin findByEmail(String email);

}
