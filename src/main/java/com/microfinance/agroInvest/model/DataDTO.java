package com.microfinance.agroInvest.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DataDTO {

        private Long id;
        private String titre;
        private String montant;
        private String dateDebut;
        private String durre;
        private String description;
        private  String audioDescriptionPath;


}
