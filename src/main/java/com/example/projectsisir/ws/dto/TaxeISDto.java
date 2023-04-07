package com.example.projectsisir.ws.dto;

import com.example.projectsisir.bean.Societe;
import com.example.projectsisir.bean.TauxTaxeIS;
import lombok.Data;

import java.util.Date;

@Data
public class TaxeISDto {
    private long id;
    private int annee;
    private int trimestre;
    private Societe societe;
    private double ChiffreAffaire;
    private double charge;
    private double resultatAvantImpot;
    private double resultatApresImpot;
    private double montantIs;
    private Date DateEcheance;
    private Date DatePaiement;
    private TauxTaxeIS tauxTaxeIS;

}
