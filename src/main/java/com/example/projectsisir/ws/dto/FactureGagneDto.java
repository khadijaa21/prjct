package com.example.projectsisir.ws.dto;

import com.example.projectsisir.bean.Societe;
import com.example.projectsisir.bean.TaxeIS;
import lombok.Data;

import java.util.Date;
@Data
public class FactureGagneDto {

    private Long id;
    private String code;

    private double montantHT;
    private double montant;
    private double montantTTC;
    private double tva;
    private Date dateFacture;

    private Societe societe;
    private TaxeIS taxeIS;

}