package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "MicroCredi")
public class Credit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCredit;
   @Column(name = "Nom")
    @NotNull(message = "Le champ nom est obligatoire")
    private String nom;

    @Column(name = "Montant")
    @NotNull(message = "le champ montant est obligatoire")
    private int montant;

    @Column(name = "Date de Debut")
    @NotNull(message = "le champ dateDebut est obligatoire")
    private String dateDebut;

    @Column(name = "Date de Fin")
    @NotNull(message = "le champ dateFin est obligatoire")
    private String dateFin;

    @Column(name = "Description")
    @NotNull(message = "le champ d'escription est obligatoire")
    private String description;


}
