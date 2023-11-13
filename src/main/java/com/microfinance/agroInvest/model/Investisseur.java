package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@PrimaryKeyJoinColumn(name = "idUser")
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "investisseur")

public class Investisseur {
    //private final long serialVersionUID=1L;
    @Column(name = "idInvestisseur")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInv;

    @Column(name = "nom prenom")
    @NotNull(message = "Le champ nom et prenom est obligatoire")
    private  String nomPrenom;

    @Column(name = "email")
    @NotNull(message = "le champ email est obligatoire")
    @Email(message = "Entrer une adress email valide")
    private String email;

    @Column(name = "telephone")
    @NotNull(message = "le champ tel est obligatoir")
    @Size(min = 7 ,max =8 , message = "entrer un numéro de telephone correct")
    private int telephone;

    @Column(name = "residense")
    @NotNull(message = "residense obligatoir")
    private String residense;

    @Column(name = "image")
    @NotNull(message = "image obligatoir")
    private String image;

    @Column(name = "motDePasse")
    @NotNull(message = "le champ mot de passe est obligatoire")
    private String passWord;

    @Column(name = "motDePasseConfirm")
    @NotNull(message = "le champ mot de passe confirm est obligatoire")
    private String passWordConfirm;
}
