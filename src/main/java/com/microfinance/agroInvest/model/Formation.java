package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "Formations")
public class Formation {
    @Column(name = "idFormation")

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFor;

    @NotNull(message = "le champ nom est obligatoir")
    @Column(name = "Nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @NotNull(message = "le champ nom est obligatoir")
    @Column(name = "video")
    private String video;

}
