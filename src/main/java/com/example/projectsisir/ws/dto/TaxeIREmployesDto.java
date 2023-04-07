package com.example.projectsisir.ws.dto;

import com.example.projectsisir.bean.Employe;
import com.example.projectsisir.bean.TauxTaxeIR;
import com.example.projectsisir.bean.TaxeIR;
import lombok.Data;

@Data
public class TaxeIREmployesDto {


    private Long id;

    private TauxTaxeIR tauxTaxeIR;

    private TaxeIR taxeIR;
    private double salaireNet;
    private double salaireBrute;
    private double montantIR;
    private Boolean employed ;

    private Employe employe;
}
