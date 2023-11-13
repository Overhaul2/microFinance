package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Utilisateur;
import com.microfinance.agroInvest.repository.RepositoryUtilisateur;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements IUtilisateurService{
    
    private RepositoryUtilisateur repositoryUtilisateur; //injection
    @Override
    public String inscrireAgriculteur(Utilisateur utilisateur) {
        Utilisateur utilisateur1 = repositoryUtilisateur.findByEmailAndTelephone(utilisateur.getEmail(), utilisateur.getTelephone());
        if (utilisateur1 == null) {
            repositoryUtilisateur.save(utilisateur);
            return "Utilisateur inscrit !";
        }else {
            return "Cet utilisateur existe deja";
        }
    }

    @Override
    public String inscrireInvestisseur(Utilisateur utilisateur) throws NotFoundException {
        Utilisateur utilisateur1 = repositoryUtilisateur.findByEmailAndTelephone(utilisateur.getEmail(), utilisateur.getTelephone());
        if (utilisateur1==null){
            repositoryUtilisateur.save(utilisateur);
            return "Utilisateur incrit";
        }else {
          throw new NotFoundException("Utilisateur existe déjà");
        }
    }

    @Override
    public String connection(String email, String passWord) throws NotFoundException {
        Utilisateur utilisateur = repositoryUtilisateur.findByEmailAndPassWord(email, passWord);
        if (utilisateur!=null){
            return "utilisateur connecter !";
        }else {
            throw new NotFoundException("Adress email ou mot de passe incorrect");
        }

    }

    @Override
    public List<Utilisateur> afficherTout() {
        return repositoryUtilisateur.findAll();
    }

    @Override
    public Utilisateur lire(Long idUser) {
        return repositoryUtilisateur.findByIdUser(idUser);
    }

    @Override
    public String modiffier(Utilisateur utilisateur) throws NotFoundException {
        Utilisateur utilisateur1 = repositoryUtilisateur.findByEmail(utilisateur.getEmail());
        if (utilisateur1!=null){
            repositoryUtilisateur.save(utilisateur);
            return "Utilisateur modiffier !";
        }else {
            throw new NotFoundException("un utilisateur existe déja avec le même adress email");
        }
    }

    @Override
    public String supprimer(Long idUser) throws NotFoundException {
        Utilisateur utilisateur = repositoryUtilisateur.findByIdUser(idUser);
        if (utilisateur==null){
            throw new NotFoundException("cet utilisateur n'existe pas");
        }else {
            repositoryUtilisateur.deleteById(idUser);
            return "Utilisateur supprimer avec succes";
        }
    }
}
