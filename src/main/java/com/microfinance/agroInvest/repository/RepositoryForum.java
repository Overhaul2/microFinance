package com.microfinance.agroInvest.repository;

import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.model.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryForum extends JpaRepository<Forum ,Long> {

    Forum findByNom(String nom);
    Forum findForumByidF(Long idF);
}
