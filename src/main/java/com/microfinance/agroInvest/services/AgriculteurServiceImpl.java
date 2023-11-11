package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.repository.RepositoryAgriculteur;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@AllArgsConstructor
public class AgriculteurServiceImpl implements IAgriculteurService {

    private RepositoryAgriculteur repositoryAgriculteur;
    @Override
    public Agriculteur inscrire(Agriculteur agriculteur, MultipartFile imageFile) throws Exception {
       Agriculteur agriculteur1= repositoryAgriculteur.findByEmailAndTelephone(agriculteur.getEmail(), agriculteur.getTelephone());
        if (agriculteur1!=null){
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
                        agriculteur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                    }else{
                        try {
                            String nom = location+"\\"+imageFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(imageFile.getInputStream(),
                                        rootlocation.resolve(imageFile.getOriginalFilename()));
                                agriculteur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(imageFile.getInputStream(),rootlocation.resolve(imageFile.getOriginalFilename()));
                                agriculteur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                            }
                        }catch (Exception e){
                            throw new Exception("Impossible de télécharger l\'audio");
                        }
                    }
                } catch (Exception e){
                    throw new Exception(e.getMessage());
                }
            }
            return repositoryAgriculteur.save(agriculteur);
        }

    }


    public Agriculteur modiffier(Agriculteur agriculteur,long idAgr, MultipartFile imageFile) throws Exception {
        Agriculteur agriculteur1 = repositoryAgriculteur.findByIdAgr(idAgr);
       // Agriculteur agriculteur2= repositoryAgriculteur.findByEmailAndTelephone(agriculteur.getEmail(),agriculteur.getTelephone() );
        agriculteur1.setNomPrenom(agriculteur.getNomPrenom());
        agriculteur1.setEmail(agriculteur.getEmail());
        agriculteur1.setTelephone(agriculteur.getTelephone());
        agriculteur1.setResidense(agriculteur.getResidense());
        agriculteur1.setActiviteMenee(agriculteur.getActiviteMenee());
        agriculteur1.setPassWord(agriculteur.getPassWord());
        agriculteur1.setPassWordConfirm(agriculteur.getPassWordConfirm());
        if (imageFile != null) {
            String location = "C:\\xampp\\htdocs\\image_agriculteur";
            try {
                Path rootLocation = Paths.get(location);
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                    Files.copy(imageFile.getInputStream(),
                            rootLocation.resolve(imageFile.getOriginalFilename()));
                    agriculteur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + imageFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(imageFile.getInputStream(),
                                    rootLocation.resolve(imageFile.getOriginalFilename()));
                            agriculteur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(imageFile.getInputStream(), rootLocation.resolve(imageFile.getOriginalFilename()));
                            agriculteur.setImage("http://localhost/image_agriculteur/"+imageFile.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new NotFoundException("impossible de telecharger le fichier audio");
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
       //if (agriculteur2==null){
         //   repositoryAgriculteur.save(agriculteur1);
        //}else {
          //throw new Exception("le nom existe dejà");
        //}
        return agriculteur1;
    }

    @Override
    public String connexion(String email, String password) {
       Agriculteur agriculteur= repositoryAgriculteur.findByEmailAndPassWord(email,password);
       if (agriculteur!=null){
           return "Agriculteur connecter";
       }else {
           return "email ou mot de passe incorrect";
       }

    }

    @Override
    public Agriculteur lire(Long idAgr) {
        return repositoryAgriculteur.findByIdAgr(idAgr);
    }

    @Override
    public List<Agriculteur> affichertout() {
        return repositoryAgriculteur.findAll();
    }

    @Override
    public Agriculteur supprimer(Long idAgr) throws NotFoundException {
        Agriculteur agriculteur=repositoryAgriculteur.findByIdAgr(idAgr);
        if (agriculteur==null){
            throw new NotFoundException("aucun agriculteur trouvé");
        }else {
            repositoryAgriculteur.deleteById(idAgr);
        }
        return agriculteur;
    }
}
