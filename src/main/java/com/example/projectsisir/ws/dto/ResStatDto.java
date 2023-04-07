package com.example.projectsisir.ws.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResStatDto {
    private int annee ;
    private int mois ;

    private BigDecimal mt ;


    public ResStatDto(int annee, int mois, BigDecimal mt) {
        this.annee = annee;
        this.mois = mois;
        this.mt = mt;
    }
}

