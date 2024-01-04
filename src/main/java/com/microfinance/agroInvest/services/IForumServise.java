package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Forum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IForumServise {

    Forum ajouter(Forum forum, MultipartFile imageFile) throws Exception;

    Forum modffier(Forum forum, Long idF,MultipartFile imageFile) throws Exception;

    List<Forum> affichertout();

    Forum lire(Long idF);

    String supprimer(Long idF) throws Exception;
}
