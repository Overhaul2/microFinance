package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@PrimaryKeyJoinColumn(name = "idUser")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "agriculteur")
public class Agriculteur  {
//private final long serialVersionUID=1L;
@Column(name = "idAgriculteur")
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long idAgr;

    @Column(name = "nom prenom")
    @NotNull(message = "Le champ nom et prenom est obligatoire")
    @Size(max = 80, message = "nom et prenom trop long", min = 10)
    private  String nomPrenom;

    @Column(name = "email",unique = true)
    @NotNull(message = "le champ email est obligatoire")
    @Email(message = "Entrer une adress email valide")
    private String email;

    @Column(name = "telephone")
    @NotNull(message = "le champ tel est obligatoir")
    private int telephone;

    @Column(name = "residense")
    @NotNull(message = "le champ résidense est obligatoir")
    private String residense;

    @Column(name = "age")
    @NotNull(message = "le champ age est obligatoire")
    private int age;

    @Column(name = "activiteMenee")
    @NotNull(message = "le champ Activitée menée est obligatoire")
    private String ActiviteMenee;

    @Column(name = "image")
   // @NotNull(message = "le champ image menée est obligatoire")
    private String image;

    @Column(name = "motDePasse")
    @NotNull(message = "le champ mot de passe est obligatoire")
    private String passWord;

    @Column(name = "motDePasseConfirm")
    @NotNull(message = "le champ mot de passe confirm est obligatoire")
    private String passWordConfirm;


}
