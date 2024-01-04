package com.microfinance.agroInvest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "Administrateur")
public class Admin {
    @Column(name = "idAdmin")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAd;

    @NotNull(message = "touts les champs sont obligatoir")
    @Column(name = "Nom")
    private String nom;

    @NotNull(message = "touts les champs sont obligatoir")
    @Column(name = "Prenom")
    private String prenom;

    @NotNull(message = "touts les champs sont obligatoir")
    @Column(name = "Email")
    private String email;

    @NotNull(message = "touts les champs sont obligatoir")
    @Column(name = "Mot de passe")
    private String password;

    @OneToMany(mappedBy = "formationAdmin")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Formation> adminFormation;

}
