package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Forum Discussion")
public class Forum {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFormation")
    private Long idF;

    @Column(name = "Nom")
    @NotNull(message = "le champ nom est obligatoire")
    private String nom;

    @Column(name = "Description")
    @NotNull(message = "le champ nom est obligatoire")
    private String description;

    @Column(name = "photo")
    private String image;

    @ManyToOne
    private Agriculteur agriculteurForum;

    @ManyToOne
    private Investisseur investisseurForum;

}
