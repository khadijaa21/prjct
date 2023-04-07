package com.example.projectsisir.bean;



import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Data

public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String cin;

    private String nom;

    @ManyToOne
    private Societe societe;

    private String prenom;

    private double salaire;


}
