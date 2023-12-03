package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.Configuration.EmailSender;
import com.microfinance.agroInvest.model.Agriculteur;
import com.microfinance.agroInvest.model.Offre;
import com.microfinance.agroInvest.repository.RepositoryOffre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service @AllArgsConstructor
public class OffreServiceImpl implements IOffreService{
    @Autowired
    private RepositoryOffre repositoryOffre;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private AgriculteurServiceImpl agriculteurService;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Offre AjouterOffre(Offre offre, MultipartFile audioFile) throws Exception {
        Offre offre1 = repositoryOffre.findByTitre(offre.getTitre());

        if (offre1 != null) {
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
                        offre.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                    }else{
                        try {
                            String nom = location+"\\"+audioFile.getOriginalFilename();
                            Path name = Paths.get(nom);
                            if(!Files.exists(name)){
                                Files.copy(audioFile.getInputStream(),
                                        rootlocation.resolve(audioFile.getOriginalFilename()));
                                offre.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                            }else{
                                Files.delete(name);
                                Files.copy(audioFile.getInputStream(),rootlocation.resolve(audioFile.getOriginalFilename()));
                                offre1.setAudioDescriptionPath("http://localhost/audio_description/"+audioFile.getOriginalFilename());
                            }
                        }catch (Exception e){
                            throw new Exception(e.getMessage());
                        }
                    }
                } catch (Exception e){
                    throw new Exception(e.getMessage());
                }
            }
             repositoryOffre.save(offre);
            emailSender.AlerteAgriculteur("Une Nouvelle offre de credit a été ajouter par un investisseur", "Nouvelle Offre");
            return offre;

        }
    }


    @Override
    public Offre ModiffierOffre(Offre offre, long idOf, MultipartFile audioFile) throws Exception {
        return null;
    }

    @Override
    public List<Offre> afficherTout() {
        return repositoryOffre.findAll();
    }

    @Override
    public Offre lire(Long idOf) {
        return null;
    }

    @Override
    public Offre Supprimer(Long idOf) throws Exception {
        return null;
    }

    @Override
    public List<Offre> getOffreWithNullAgriculteur() {
        String jpql="SELECT c FROM Offre c WHERE c.agriculteur IS NULL";
        TypedQuery<Offre> query= entityManager.createQuery(jpql, Offre.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void addAgriculteurToOffre(Long idOf, Long idAgr) {
        Offre offre = entityManager.find(Offre.class, idOf);
        Agriculteur agriculteur = entityManager.find(Agriculteur.class, idAgr);
        if (offre!=null && agriculteur!=null){
            offre.setAgriculteur(agriculteur);
            entityManager.merge(offre);
        }
    }

    public List<Offre> lireParIvestisseur(Long idInv){
        List<Offre> offre = repositoryOffre.findByInvestisseurIdInv(idInv);
        if (offre.isEmpty())
            throw new EntityNotFoundException("Aucun credit trouvée");
        return offre;

    }

}
