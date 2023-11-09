package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.exception.NotFoundException;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.repository.RepositoryCredit;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor


public class CreditServiceImpl implements ICreditService  {
    @Autowired
    private RepositoryCredit repositoryCredit;


    @Override
    public Credit AjouterDemande(Credit credit, MultipartFile audioFile) throws Exception {
        Credit credit1 = repositoryCredit.findByNom(credit.getNom());
        if (credit1 != null) {
            throw new Exception("un demande avec le même nom existe déjà");
        } else {
            if(audioFile != null){
                String location = "C:\\xampp\\htdocs\\audio_description";
                try{
                    Path rootlocation = Paths.get(location);
                    if(!Files.exists(rootlocation)){
                        Files.createDirectories(rootlocation);
                        Files.copy(audioFile.getInputStream(),
                                rootlocation.resolve(audioFile.getOriginalFilename()));
                        credit.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                    }else{
                        try {
                            String nom = location+"\\"+audioFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(audioFile.getInputStream(),
                                        rootlocation.resolve(audioFile.getOriginalFilename()));
                                credit.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(audioFile.getInputStream(),rootlocation.resolve(audioFile.getOriginalFilename()));
                                credit.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                            }
                        }catch (Exception e){
                            throw new Exception("Impossible de télécharger l\'audio");
                        }
                    }
                } catch (Exception e){
                    throw new Exception(e.getMessage());
                }
            }
            return repositoryCredit.save(credit);

        }
    }

    public Credit ModiffierDemande(Credit credit, long id, MultipartFile audioFile) throws Exception {
        Credit credit1 = repositoryCredit.findByIdCredit(id);
        Credit credit2= repositoryCredit.findByNom(credit.getNom());
        credit1.setNom(credit.getNom());
        credit1.setDescription(credit.getDescription());
        credit1.setMontant(credit.getMontant());
        credit1.setDateDebut(credit.getDateDebut());
        credit1.setDateFin(credit.getDateFin());
        if (audioFile != null) {
            String location = "C:\\xampp\\htdocs\\audio_description";
            try {
                Path rootLocation = Paths.get(location);
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                    Files.copy(audioFile.getInputStream(),
                            rootLocation.resolve(audioFile.getOriginalFilename()));
                    credit1.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + audioFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(audioFile.getInputStream(),
                                    rootLocation.resolve(audioFile.getOriginalFilename()));
                            credit1.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(audioFile.getInputStream(), rootLocation.resolve(audioFile.getOriginalFilename()));
                            credit1.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new NotFoundException("impossible de telecharger le fichier audio");
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        if (credit2==null){
            repositoryCredit.save(credit1);
        }else {
            throw new Exception("le nom existe dejà");
        }
        return credit1;
    }



    public List<Credit> afficherTout() {
        return repositoryCredit.findAll();
    }


    public Credit lire(Long idCredit) {
        return repositoryCredit.findByIdCredit(idCredit);
    }


    public Credit Supprimer(Long idCredit) throws NotFoundException {
        Credit credit= repositoryCredit.findByIdCredit(idCredit);
        if (credit.getIdCredit()==null) {
            throw new NotFoundException("Cette demande n'existe pas");
        }else {
            repositoryCredit.deleteById(idCredit);
        }
        return credit;
    }
}
