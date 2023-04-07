package com.example.projectsisir.ws.dto;

import com.example.projectsisir.bean.Societe;
import com.example.projectsisir.bean.TaxeIREmployes;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaxeIRDto {


    private Long id;
    private int mois;
    private int annee;
    private Societe societe;
    private Date dateDeclaration;//societe declare

    private double salaireNet;//total des salaires net de tout les employes
    private double salaireBrute;
    private double montantIR;
    private List<TaxeIREmployes> taxeIREmployes;




}
