package com.example.projectsisir.ws.dto;

import com.example.projectsisir.bean.FactureGagne;
import com.example.projectsisir.bean.FacturePerte;
import com.example.projectsisir.bean.TaxeIS;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class ISItemDto {

    private Long id;

    TaxeIS taxeIS;
    List<FactureGagne> factureGagnes ;

    List<FacturePerte> facturePertes;
}
