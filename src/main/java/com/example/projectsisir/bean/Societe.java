package com.example.projectsisir.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data

@Entity

public class Societe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String libelle;
    private String ice;
    private String description;
    @ManyToOne
    private Employe employe;

}
