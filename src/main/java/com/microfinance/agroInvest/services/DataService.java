package com.microfinance.agroInvest.services;

import com.microfinance.agroInvest.model.Credit;
import com.microfinance.agroInvest.model.DataDTO;
import com.microfinance.agroInvest.model.Offre;
import com.microfinance.agroInvest.repository.RepositoryCredit;
import com.microfinance.agroInvest.repository.RepositoryOffre;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class DataService {
    @Autowired
    private RepositoryOffre repositoryOffre;
    @Autowired
    private RepositoryCredit repositoryCredit;

    public List<DataDTO> getAllDataByAgriculteur(Long idAgr){
        List<Offre> offres = repositoryOffre.findByAgriculteurIdAgr(idAgr);
        List<Credit> credits = repositoryCredit.findByAgriculteurIdAgr(idAgr);

        List<DataDTO> offreDTOs = offres.stream().map(offre -> new DataDTO(offre.getIdOf(),offre.getTitre(),offre.getMontant(),offre.getDateDebut(),offre.getDurre(),offre.getDescription(),offre.getAudioDescriptionPath())).collect(Collectors.toList());
        List<DataDTO> creditDTOs = credits.stream().map(credit -> new DataDTO(credit.getIdCredit(),credit.getTitre(),credit.getMontant(),credit.getDateDebut(),credit.getDurre(),credit.getDescription(),credit.getAudioDescriptionPath())).collect((Collectors.toList()));
        List<DataDTO> combineData=new ArrayList<>(offreDTOs);
        combineData.addAll(creditDTOs);

        return combineData;
    }

    public List<DataDTO> getAllDataByInvestisseur(Long idInv) {
        List<Offre> offres = repositoryOffre.findByInvestisseurIdInv(idInv);
        List<Credit> credits = repositoryCredit.findByOffreInvestisseurIdInv(idInv);

        List<DataDTO> offreDTOs = offres.stream().map(offre -> new DataDTO(offre.getIdOf(), offre.getTitre(), offre.getMontant(), offre.getDateDebut(), offre.getDurre(), offre.getDescription(), offre.getAudioDescriptionPath())).collect(Collectors.toList());
        List<DataDTO> creditDTOs = credits.stream().map(credit -> new DataDTO(credit.getIdCredit(), credit.getTitre(), credit.getMontant(),credit.getDateDebut(), credit.getDurre(), credit.getDescription(), credit.getAudioDescriptionPath())).collect((Collectors.toList()));
        List<DataDTO> combineData = new ArrayList<>(offreDTOs);
        combineData.addAll(creditDTOs);

        return combineData;
    }

}
