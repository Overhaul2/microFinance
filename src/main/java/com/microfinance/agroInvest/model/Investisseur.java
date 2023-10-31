package com.microfinance.agroInvest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@PrimaryKeyJoinColumn(name = "idUser")
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "investisseur")

public class Investisseur extends Utilisateur  {
    private final long serialVersionUID=1L;
   // @ManyToOne
    //@JoinColumn(name = "idInvestisseur", referencedColumnName = "idUtilisateur")
    //private utilisateur utilisateur;
    private int sol;
}
