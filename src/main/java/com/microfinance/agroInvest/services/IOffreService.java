package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.model.Offre;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IOffreService {

    Offre AjouterOffre (Offre offre, MultipartFile audioFile) throws Exception;
    Offre ModiffierOffre(Offre offre,long idOf,MultipartFile audioFile) throws Exception;

    List<Offre> afficherTout();

    Offre lire(Long idOf);

    Offre Supprimer(Long idOf) throws Exception;

    List<Offre> getOffreWithNullAgriculteur();

    void addAgriculteurToOffre(Long idOf, Long idAgr);

}
