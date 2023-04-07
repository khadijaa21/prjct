package com.example.projectsisir.ws.dto;

import com.example.projectsisir.bean.Employe;
import lombok.Data;

@Data
public class SocieteDto {
    private Long id;
    private String libelle;
    private String ice;
    private String description;
    private Employe employe;
}
