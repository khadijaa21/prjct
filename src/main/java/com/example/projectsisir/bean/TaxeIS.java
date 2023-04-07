package com.example.projectsisir.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class TaxeIS {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int annee;

    private int trimestre;
    @ManyToOne
    private Societe societe;

    private double ChiffreAffaire;
    private double charge;
    private Date DateEcheance;
    private Date DatePaiement;

    private double resultatAvantImpot;
    private double resultatApresImpot;
    private double montantIs;

    @ManyToOne
    private TauxTaxeIS tauxTaxeIS;

    @OneToMany(mappedBy ="taxeIS")
    private List<NotificationISDetail> notificationISDetails;
}
