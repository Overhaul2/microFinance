package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Formation;
import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.repository.RepositoryFormation;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements IFormationService{

    private RepositoryFormation repositoryFormation;
    @Override
    public Formation ajouter(Formation formation, MultipartFile videoFile) throws Exception {
        Formation formation1= repositoryFormation.findByNom(formation.getNom());
        if (formation1!=null){
            throw new RuntimeException("Une formation existe deja avec le même nom");
        }else {
            if(videoFile != null){
                String location = "C:\\xampp\\htdocs\\video_Formation";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(videoFile.getInputStream(),
                                rootlocation.resolve(videoFile.getOriginalFilename()));
                        formation.setVideo("http://localhost/video_Formation/"+videoFile.getOriginalFilename());
                    }else{
                        try {
                            String nom = location+"\\"+videoFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(videoFile.getInputStream(),
                                        rootlocation.resolve(videoFile.getOriginalFilename()));
                                formation.setVideo("http://localhost/video_Formation/"+videoFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(videoFile.getInputStream(),rootlocation.resolve(videoFile.getOriginalFilename()));
                                formation.setVideo("http://localhost/video_Formation/"+videoFile.getOriginalFilename());
                            }
                        }catch (Exception e){
                            throw new Exception("Impossible de télécharger la\' videio");
                        }
                    }
                } catch (Exception e){
                    throw new Exception(e.getMessage());
                }
            }
            return repositoryFormation.save(formation);
        }

    }

    @Override
    public Formation modiffier(Formation formation, Long idFor, MultipartFile videoFile) throws Exception {
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

        Formation formation1 = repositoryFormation.findFormationByIdFor(formation.getIdFor());
        System.out.println(formation1);
            Formation formation2= repositoryFormation.findByNom(formation.getNom());
            formation1.setNom(formation.getNom());
            formation.setDescription(formation.getDescription());
            formation1.setNom(formation.getNom());
            formation1.setDescription(formation.getDescription());
            formation.setVideo(formation.getVideo());
            if (videoFile != null) {
                String location = "C:\\xampp\\htdocs\\video_Formation";
                try {
                    Path rootLocation = Paths.get(location);
                    if (!Files.exists(rootLocation)) {
                        Files.createDirectories(rootLocation);
                        Files.copy(videoFile.getInputStream(),
                                rootLocation.resolve(videoFile.getOriginalFilename()));
                        formation.setVideo("http://localhost/video_Formation/"+videoFile.getOriginalFilename());
                    } else {
                        try {
                            String nom = location + "\\" + videoFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if (!Files.exists(name)) {
                                Files.copy(videoFile.getInputStream(),
                                        rootLocation.resolve(videoFile.getOriginalFilename()));
                                formation.setVideo("http://localhost/video_Formation/"+videoFile.getOriginalFilename());
                            } else {
                                Files.delete(name);
                                Files.copy(videoFile.getInputStream(), rootLocation.resolve(videoFile.getOriginalFilename()));
                                formation1.setVideo("http://localhost/video_Formation/"+videoFile.getOriginalFilename());
                            }
                        } catch (Exception e) {
                            throw new NotFoundException("impossible de telecharger le fichier audio");
                        }
                    }
                } catch (Exception e) {
                    throw new Exception(e.getMessage());
                }
            }
            if (formation2==null){
                repositoryFormation.save(formation1);
            }else {
                throw new Exception("le nom existe dejà");
            }
            return formation1;
        }

    @Override
    public Formation lire(Long idFor) {
        return repositoryFormation.findFormationByIdFor(idFor);
    }

    @Override
    public List<Formation> affichertout() {
        return repositoryFormation.findAll();
    }

    @Override
    public Formation supprimer(Long idFor) throws NotFoundException {
        Formation formation = repositoryFormation.findFormationByIdFor(idFor);
        if (formation!=null){
            repositoryFormation.deleteById(idFor);

        }else {
            throw new NotFoundException("Aucune formation avec n'existe avec ID :"+idFor);
        }
        return formation;
    }
}
