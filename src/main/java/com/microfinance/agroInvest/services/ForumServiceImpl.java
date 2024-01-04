package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Forum;
import com.microfinance.agroInvest.repository.RepositoryForum;
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
public class ForumServiceImpl implements IForumServise{
    @Autowired
    private RepositoryForum repositoryForum;
    @Override
    public Forum ajouter(Forum forum,MultipartFile imageFile) throws Exception {
        Forum forum1=repositoryForum.findByNom(forum.getNom());
        if (imageFile != null) {
            String location = "C:\\xampp\\htdocs\\image_Forum";
            try {
                Path rootLocation = Paths.get(location);
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                    Files.copy(imageFile.getInputStream(),
                            rootLocation.resolve(imageFile.getOriginalFilename()));
                    forum1.setImage("http://localhost/image_Forum/"+imageFile.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + imageFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(imageFile.getInputStream(),
                                    rootLocation.resolve(imageFile.getOriginalFilename()));
                            forum1.setImage("http://localhost/image_Forum/"+imageFile.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(imageFile.getInputStream(), rootLocation.resolve(imageFile.getOriginalFilename()));
                            forum1.setImage("http://localhost/image_Forum/"+imageFile.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new Exception(e.getMessage());
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        if (forum1!=null){
            throw new Exception("un Forum existe deja avec le même nom");
        }
        return repositoryForum.save(forum);
    }

    @Override
    public Forum modffier(Forum forum, Long idF,MultipartFile imageFile) throws Exception {
        Forum forum1= repositoryForum.findForumByidF(forum.getIdF());
        forum1.setNom(forum.getNom());
        forum1.setDescription(forum.getDescription());
        forum1.setImage(forum1.getImage());
        if (imageFile != null) {
            String location = "C:\\xampp\\htdocs\\image_Forum";
            try {
                Path rootLocation = Paths.get(location);
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                    Files.copy(imageFile.getInputStream(),
                            rootLocation.resolve(imageFile.getOriginalFilename()));
                    forum1.setImage("http://localhost/image_Forum/"+imageFile.getOriginalFilename());
                } else {
                    try {
                        String nom = location + "\\" + imageFile.getOriginalFilename();
                        Path name = Paths.get(nom);
                        if (!Files.exists(name)) {
                            Files.copy(imageFile.getInputStream(),
                                    rootLocation.resolve(imageFile.getOriginalFilename()));
                            forum1.setImage("http://localhost/image_Forum/"+imageFile.getOriginalFilename());
                        } else {
                            Files.delete(name);
                            Files.copy(imageFile.getInputStream(), rootLocation.resolve(imageFile.getOriginalFilename()));
                            forum1.setImage("http://localhost/image_Forum/"+imageFile.getOriginalFilename());
                        }
                    } catch (Exception e) {
                        throw new Exception(e.getMessage());
                    }
                }
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }

        if (forum1==null){
            throw new Exception("Forum non trouver");
        }else
        return repositoryForum.save(forum1);
    }

    @Override
    public List<Forum> affichertout() {
        return repositoryForum.findAll();
    }

    @Override
    public Forum lire(Long idF) {
        return repositoryForum.findForumByidF(idF);
    }

    @Override
    public String supprimer(Long idF) throws Exception {
        Forum forum=repositoryForum.findForumByidF(idF);
        if (forum==null){
            throw new Exception("aucune forum disponible avec cet id");
        }else {
            repositoryForum.deleteById(idF);
            return "forum supprimer avec succès";
        }

    }
}
