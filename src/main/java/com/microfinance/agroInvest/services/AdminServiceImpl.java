package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Admin;
import com.microfinance.agroInvest.repository.RepositoryAdmin;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AdminServiceImpl implements IAdminService{
    @Autowired
    private RepositoryAdmin repositoryAdmin;
    @Override
    public Admin inscrire(Admin admin) throws Exception {
        return null;
    }

    @Override
    public Admin modiffier(Admin admin, Long idAd) throws Exception {
        return null;
    }

    @Override
    public Admin connexion(String email, String password) throws Exception {
        Admin admin= repositoryAdmin.findByEmailAndPassword(email,password);
        if (admin==null){
            throw new Exception("Cet administrateur n'existe pas");
        }else {
            return admin;
        }
    }

    @Override
    public Admin lire(Long idAd) {
        return null;
    }

    @Override
    public List<Admin> affichertout() {
        return repositoryAdmin.findAll();
    }

    @Override
    public Admin supprimer(Long idAd) throws NotFoundException {
        return null;
    }
}
