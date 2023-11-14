package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Forum;
import com.microfinance.agroInvest.repository.RepositoryForum;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@AllArgsConstructor
public class ForumServiceImpl implements IForumServise{
    @Autowired
    private RepositoryForum repositoryForum;
    @Override
    public Forum ajouter(Forum forum, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public Forum modffier(Forum forum, Long idF, MultipartFile multipartFile) {
        return null;
    }

    @Override
    public List<Forum> affichertout() {
        return null;
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
