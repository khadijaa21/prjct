package com.example.projectsisir.bean;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class TauxTaxeIR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date dateApplicationDebut;
    private Date dateApplicationFin;
    private double pourcentage;
    private Double salaireMax;

    private double salaireMin;



}
