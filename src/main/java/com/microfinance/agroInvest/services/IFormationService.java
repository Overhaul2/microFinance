package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Formation;
import com.microfinance.agroInvest.exception.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFormationService {
    Formation ajouter(Formation formation, MultipartFile videoFile) throws Exception;

    Formation modiffier(Formation formation,Long idFor, MultipartFile videoFile) throws Exception;

    Formation lire(Long idFor);

    List<Formation> affichertout();

    Formation supprimer(Long idFor) throws NotFoundException;
}
