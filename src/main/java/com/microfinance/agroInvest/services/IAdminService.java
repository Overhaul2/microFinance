package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Admin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IAdminService {

    Admin inscrire(Admin admin) throws Exception;

    Admin modiffier(Admin admin,Long idAd) throws Exception;

    Admin connexion(String email, String password) throws Exception;

    Admin lire(Long idAd);

    List<Admin> affichertout();

    Admin supprimer(Long idAd) throws NotFoundException;
}
