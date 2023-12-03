package com.microfinance.agroInvest.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Offre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idOf;
    @Column(name = "titre")
    @NotNull(message = "Le champ nom est obligatoire")
    private String titre;

    @Column(name = "Montant")
    @NotNull(message = "le champ montant est obligatoire")
    private String montant;

    @Column(name = "Date de Debut")
    @NotNull(message = "le champ dateDebut est obligatoire")
    private Date dateDebut;

    @Column(name = "Durre")
    @NotNull(message = "le champ dateFin est obligatoire")
    private int durre;

    @Column(name = "Description")
    @NotNull(message = "le champ d'escription est obligatoire")
    private String description;

    @Column(name = "DescriptionAudio")
    private String audioDescriptionPath;

    private String etat= "En attente";

    @ManyToOne
    private Agriculteur agriculteur;

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Investisseur investisseur;
}
