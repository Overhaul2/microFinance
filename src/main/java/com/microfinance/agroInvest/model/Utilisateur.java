package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "utilisateur")
public abstract class Utilisateur implements Serializable {
    private static final long serialVersionUID=1L;
    @Column(name = "idUtilisateur")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "nom prenom")
    @NotNull(message = "Le champ nom et prenom est obligatoire")
    @Size(max = 80, message = "nom et prenom trop long", min = 10)
    private  String nomPrenom;

    @Column(name = "email",unique = true)
    @NotNull(message = "le champ email est obligatoire")
    @Size(max = 50, message = "email trop long")
    @Email(message = "Entrer une adress email valide")
    private String email;

    @Column(name = "telephone")
    @NotNull(message = "le champ tel est obligatoir")
    @Size(min = 7 ,max =8 , message = "entrer un numéro de telephone correct")
    private int telephone;

    @Column(name = "residense")
    @Size(max = 50,min = 5, message = "entrer une résidense correct")
    @NotNull(message = "le champ résidense est obligatoir")
    private String residense;

    @Column(name = "motDePasse")
    @NotNull(message = "le champ mot de passe est obligatoire")
    @Size(min = 8, max = 50, message = "entrer un mode passe correct")
    private String passWord;

    @Column(name = "motDePasseConfirm")
    @NotNull(message = "le champ mot de passe confirm est obligatoire")
    @Size(min = 8, max = 50, message = "entrer un mode passe correct")
    private String passWordConfirm;
}
