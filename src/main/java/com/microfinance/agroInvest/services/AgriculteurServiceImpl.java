package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.repository.RepositoryAgriculteur;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.parser.Entity;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@AllArgsConstructor
public class AgriculteurServiceImpl implements IAgriculteurService {
    @Autowired
    private RepositoryAgriculteur repositoryAgriculteur;
    @Override
    public Agriculteur inscrire(Agriculteur agriculteur, MultipartFile imageFile) throws Exception {
        Agriculteur agriculteur1 = repositoryAgriculteur.findByEmailAndTelephone(agriculteur.getEmail(), agriculteur.getTelephone());

        if (agriculteur1 != null) {
            throw new RuntimeException("Un agriculteur existe déjà avec le même numéro ou adresse e-mail");
        } else {
            if (imageFile != null) {
                int index = imageFile.getOriginalFilename().lastIndexOf(".");
                String location = "C:\\xampp\\htdocs\\image_agriculteur";
                try {
                    Path rootLocation = Paths.get(location);
                    if (!Files.exists(rootLocation)) {
                        Files.createDirectories(rootLocation);
                    }

                    Path imagePath = rootLocation.resolve(agriculteur.getEmail()+imageFile.getOriginalFilename().substring(index));

                    if (Files.exists(imagePath)) {
                        Files.delete(imagePath);
                    }

                    Files.copy(imageFile.getInputStream(), imagePath);
                    agriculteur.setImage("image_agriculteur/" + agriculteur.getEmail()+imageFile.getOriginalFilename().substring(index));
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }

            if (agriculteur.getPassWord().equals(agriculteur.getPassWordConfirm())) {
                return repositoryAgriculteur.save(agriculteur);
            } else {
                throw new IllegalAccessException("Les 2 mots de passe ne correspondent pas");
            }
        }
    }


    @Override
    public Agriculteur modiffier(Agriculteur agriculteur,Long idAgr, MultipartFile multipartFile) throws Exception {
        Agriculteur agriculteur1 = repositoryAgriculteur.findByIdAgr(idAgr);
                //.orElseThrow(()-> new EntityNotFoundException("agriculteur nexistipas avec id:" +idAgr ));
      // Agriculteur agriculteur2= repositoryAgriculteur.findByEmailAndTelephone(agriculteur.getEmail(),agriculteur.getTelephone() );
        agriculteur1.setNomPrenom(agriculteur.getNomPrenom());
        agriculteur1.setEmail(agriculteur.getEmail());
        agriculteur1.setTelephone(agriculteur.getTelephone());
        agriculteur1.setResidense(agriculteur.getResidense());
        agriculteur1.setAge(agriculteur.getAge());
        agriculteur1.setActiviteMenee(agriculteur.getActiviteMenee());
        agriculteur1.setPassWord(agriculteur.getPassWord());
        agriculteur1.setPassWordConfirm(agriculteur.getPassWordConfirm());
        if (multipartFile != null) {
            String location = "C:\\xampp\\htdocs\\image_agriculteur";
            try {
                Path rootLocation = Paths.get(location);
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                    Files.copy(multipartFile.getInputStream(),
                            rootLocation.resolve(multipartFile.getOriginalFilename()));
                    agriculteur.setImage("http://localhost/image_agriculteur/"+multipartFile.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + multipartFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(multipartFile.getInputStream(),
                                    rootLocation.resolve(multipartFile.getOriginalFilename()));
                            agriculteur.setImage("http://localhost/image_agriculteur/"+multipartFile.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(multipartFile.getInputStream(), rootLocation.resolve(multipartFile.getOriginalFilename()));
                            agriculteur.setImage("http://localhost/image_agriculteur/"+multipartFile.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new Exception("impossible de telecharger le fichier audio");
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
      // if (agriculteur2==null){
          //  repositoryAgriculteur.save(agriculteur1);
        //}else {
         //throw new Exception("le nom existe dejà");
        //}
    repositoryAgriculteur.save(agriculteur);
        return agriculteur1;
    }

    @Override
    public Agriculteur connexion(String email, String password) throws Exception {
       Agriculteur agriculteur= repositoryAgriculteur.findByEmailAndPassWord(email,password);
       if (agriculteur==null){
           throw new Exception("cet agriculteur n'existe pas ");
       }else {
           return agriculteur;
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
    public Agriculteur supprimer(Long idAgr) {
        Agriculteur agriculteur=repositoryAgriculteur.findByIdAgr(idAgr);
        if (agriculteur==null){
           new NotFoundException("aucun agriculteur trouvé");
        }else {
            repositoryAgriculteur.deleteById(idAgr);
        }
        return agriculteur;
    }

}
