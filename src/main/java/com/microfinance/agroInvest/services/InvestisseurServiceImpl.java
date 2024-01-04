package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.Configuration.EmailSender;
import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Investisseur;
import com.microfinance.agroInvest.repository.RepositoryInvestisseur;
import com.microfinance.agroInvest.repository.RepositoryOffre;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service @AllArgsConstructor
public class InvestisseurServiceImpl implements IInvestisseurService {

    @Autowired
    private RepositoryInvestisseur repositoryInvestisseur;


    public Investisseur inscrire(Investisseur investisseur, MultipartFile imageFile) throws Exception {
        Investisseur investisseur1= repositoryInvestisseur.findByEmailAndTelephone(investisseur.getEmail(), investisseur.getTelephone());
        if (investisseur1!=null){
            throw new RuntimeException("un agriculteur bexiste dejà avec le même numero ou adresse email");
        }else {
            if(imageFile != null){
                String location = "C:\\xampp\\htdocs\\image_agriculteur";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(imageFile.getInputStream(),
                                rootlocation.resolve(imageFile.getOriginalFilename()));
                        investisseur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                    }else{
                        try {
                            String nom = location+"\\"+imageFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(imageFile.getInputStream(),
                                        rootlocation.resolve(imageFile.getOriginalFilename()));
                                investisseur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(imageFile.getInputStream(),rootlocation.resolve(imageFile.getOriginalFilename()));
                                investisseur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                            }
                        }catch (Exception e){
                            throw new Exception("Impossible de télécharger l\'audio");
                        }
                    }
                } catch (Exception e){
                    throw new Exception(e.getMessage());
                }
            }
            return repositoryInvestisseur.save(investisseur);
        }

    }

@Override
    public Investisseur modiffier(Investisseur investisseur,Long idInv, MultipartFile multipartFile) throws Exception {
        Investisseur investisseur1 = repositoryInvestisseur.findByIdInv(idInv);
        //.orElseThrow(()-> new EntityNotFoundException("agriculteur nexistipas avec id:" +idAgr ));
       // Investisseur investisseur2= repositoryInvestisseur.findByEmailAndTelephone(investisseur.getEmail(),investisseur.getTelephone() );
        investisseur1.setNomPrenom(investisseur.getNomPrenom());
        investisseur1.setEmail(investisseur.getEmail());
        investisseur1.setTelephone(investisseur.getTelephone());
        investisseur1.setResidense(investisseur.getResidense());
        investisseur1.setPassWord(investisseur.getPassWord());
        investisseur1.setPassWordConfirm(investisseur.getPassWordConfirm());
        if (multipartFile != null) {
            String location = "C:\\xampp\\htdocs\\image_agriculteur";
            try {
                Path rootLocation = Paths.get(location);
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                    Files.copy(multipartFile.getInputStream(),
                            rootLocation.resolve(multipartFile.getOriginalFilename()));
                    investisseur.setImage("http://localhost/image_agriculteur/"+multipartFile.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + multipartFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(multipartFile.getInputStream(),
                                    rootLocation.resolve(multipartFile.getOriginalFilename()));
                            investisseur.setImage("http://localhost/image_agriculteur/"+multipartFile.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(multipartFile.getInputStream(), rootLocation.resolve(multipartFile.getOriginalFilename()));
                            investisseur.setImage("http://localhost/image_agriculteur/"+multipartFile.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new NotFoundException("impossible de telecharger le fichier audio");
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
         //if (investisseur2==null){
        //    repositoryInvestisseur.save(investisseur1);
       // }else {
         //throw new Exception("un investisseur existe dejà avec le même nom ou numéro de téléphone");
        //}
        repositoryInvestisseur.save(investisseur1);
        return investisseur1;
    }

    @Override
    public Investisseur connexion(String email, String password) throws Exception {
        Investisseur investisseur= repositoryInvestisseur.findByEmailAndPassWord(email,password);
        if (investisseur!=null){
            return investisseur;
        }else {
            throw new Exception("email ou mot de passe incorrect");
        }

    }

    @Override
    public Investisseur lire(Long idInv) {
        return repositoryInvestisseur.findByIdInv(idInv);
    }

    @Override
    public List<Investisseur> affichertout() {
        return repositoryInvestisseur.findAll();
    }

    @Override
    public Investisseur supprimer(Long idInv) throws NotFoundException {
        Investisseur investisseur=repositoryInvestisseur.findByIdInv(idInv);
        if (investisseur==null){
            throw new NotFoundException("aucun Investisseur trouvé");
        }else {
            repositoryInvestisseur.deleteById(idInv);
        }
        return investisseur;
    }
}
