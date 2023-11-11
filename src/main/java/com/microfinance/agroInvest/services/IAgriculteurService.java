package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Agriculteur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface IAgriculteurService {

    Agriculteur inscrire(Agriculteur agriculteur, MultipartFile imageFile) throws Exception;

    Agriculteur modiffier(Agriculteur agriculteur,long idAgr, MultipartFile imageFile) throws Exception;

    String connexion(String email, String password);

    Agriculteur lire(Long idAgr);

    List<Agriculteur> affichertout();

    Agriculteur supprimer(Long idAgr) throws NotFoundException;
}