package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "MicroCredit")
public class Credit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredit;
   @Column(name = "Nom")
    @NotNull(message = "Le champ nom est obligatoire")
    private String nom;

    @Column(name = "Montant")
    @NotNull(message = "le champ montant est obligatoire")
    private String montant;

    @Column(name = "Date de Debut")
    @NotNull(message = "le champ dateDebut est obligatoire")
    private Date dateDebut;

    @Column(name = "Date de Fin")
    @NotNull(message = "le champ dateFin est obligatoire")
    private Date dateFin;

    @Column(name = "Description")
    @NotNull(message = "le champ d'escription est obligatoire")
    private String description;

    @Column(name = "DescriptionAudio")
    private String audioDescriptionPath;

    @ManyToOne

    private Agriculteur creditAgriculteur;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
 private Investisseur offreInvestisseur;

}
