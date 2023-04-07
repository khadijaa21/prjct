package com.example.projectsisir.bean;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class TaxeIR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int mois;
    private int annee;

    @ManyToOne
    private Societe societe;
    private Date dateDeclaration;//societe declare

    private double salaireNet;//total des salaires net de tout les employes
    private double salaireBrute;
    private double montantIR;
    @OneToMany(mappedBy = "taxeIR")
    private List<TaxeIREmployes> taxeIREmployes;



}
