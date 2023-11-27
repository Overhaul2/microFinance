package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.repository.RepositoryAgriculteur;
import com.microfinance.agroInvest.repository.RepositoryCredit;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor


public class CreditServiceImpl implements ICreditService  {
    @Autowired
    private RepositoryCredit repositoryCredit;

    @Autowired
    private RepositoryAgriculteur repositoryAgriculteur;


    @Override
    public Credit AjouterDemande(Credit credit, MultipartFile audioFile) throws Exception {
        Credit credit1 = repositoryCredit.findByTitre(credit.getTitre());
        //verification des date lors de la demande de crédit
        //  LocalDate dateFinLimite = dateDebut.plusYears(2);
        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin = dateDebut.plusDays(30);
        LocalDate dateToDate = LocalDate.now();
       /* Agriculteur agriculteur = credit.getAgriculteur();
        if (agriculteur != null && agriculteur.getIdAgr() != null) {
            // L'ID de l'agriculteur est présent, vérifions s'il existe dans la base de données
            Optional<Agriculteur> existingAgriculteur = repositoryAgriculteur.findById(agriculteur.getIdAgr());
            if (existingAgriculteur.isPresent()) {
                // L'agriculteur existe déjà, utilisez-le
                agriculteur = existingAgriculteur.get();
                credit.setAgriculteur(agriculteur); // Mettez à jour l'agriculteur dans la demande de crédit
            } else {
                throw new Exception("Agriculteur avec l'ID spécifié non trouvé dans la base de données");
            }
        } else {
            throw new Exception("L'ID de l'agriculteur est obligatoire pour lier un agriculteur existant à la demande de crédit");
        }
*/
        if (dateDebut.isBefore(dateToDate)) {
            throw new Exception("Veuillez entrer une date valide !!!");
        }

        if (dateFin.isBefore(dateDebut)) {
            throw new Exception("La date de fin ne doit pas être antérieure à la date de début !!!");
        }

        if (dateFin.isAfter(dateDebut.plusYears(2))) {
            throw new Exception("La date de fin ne doit pas dépasser deux ans après la date de début !!!");
        }

        if (credit1 != null) {
            throw new Exception("une demande avec le même nom existe déjà");
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
                            throw new Exception(e.getMessage());
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
        //verification des date lors de la modification
        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin = dateDebut.plusDays(30);
        LocalDate dateToDate = LocalDate.now();
        if (dateDebut.isBefore(dateToDate)) {
            throw new Exception("Veuillez entrer une date valide !!!");
        }

        if (dateFin.isBefore(dateDebut)) {
            throw new Exception("La date de fin ne doit pas être antérieure à la date de début !!!");
        }

        if (dateFin.isAfter(dateDebut.plusYears(2))) {
            throw new Exception("La date de fin ne doit pas dépasser deux ans après la date de début !!!");
        }

        Credit credit1 = repositoryCredit.findByIdCredit(id);
        Credit credit2= repositoryCredit.findByTitre(credit.getTitre());
        credit1.setTitre(credit.getTitre());
        credit1.setDescription(credit.getDescription());
        credit1.setMontant(credit.getMontant());
        credit1.setDateDebut(credit.getDateDebut());
        credit1.setDurre(credit.getDurre());
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
                        throw new Exception("impossible de telecharger le fichier audio");
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


    public Credit Supprimer(Long idCredit) throws Exception {
        Credit credit= repositoryCredit.findByIdCredit(idCredit);
        if (credit.getIdCredit()==null) {
            throw new Exception("Cette demande n'existe pas");
        }else {
            repositoryCredit.deleteById(idCredit);
        }
        throw new Exception("n'existe pas");
    }

    public List<Credit> lireParAgriculteur(Long idAgr){
        List<Credit> credit = repositoryCredit.findByAgriculteurIdAgr(idAgr);
        if (credit.isEmpty())
            throw new EntityNotFoundException("Aucun credit trouvée");
        return credit;

    }
}
