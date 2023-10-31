package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@PrimaryKeyJoinColumn(name = "idUser")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "agriculteur")
public class Agriculteur extends Utilisateur  {
private final long serialVersionUID=1L;
   // @ManyToOne
   // @JoinColumn(name = "idAgriculteur", referencedColumnName="idUtilisateur")
   // private utilisateur utilisateur;

    @Column(name = "age")
    @NotNull(message = "le champ age est obligatoire")
    @Size(min = 18, max = 100, message = "Votre age n'est pas autoriser a utiliser cet application")
    private int age;

    @Column(name = "activiteMenee")
    @NotNull(message = "le champ Activitée menée est obligatoire")
    @Size(max = 150,message = "activitée trop long")
    private String ActiviteMenee;
}
