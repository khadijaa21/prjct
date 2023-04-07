package com.example.projectsisir.bean;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class TaxeIREmployes {
    public TaxeIREmployes() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long id;

    @ManyToOne
    private TauxTaxeIR tauxTaxeIR;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private TaxeIR taxeIR;
    private double salaireNet;
    private double salaireBrute;
    private double montantIR;

    @Column(columnDefinition = "boolean default false")
    private Boolean employed ;

    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Societe societe;
}
