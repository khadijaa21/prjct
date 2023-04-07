package com.example.projectsisir.service.facade;


import com.example.projectsisir.bean.FacturePerte;

import java.util.List;

public interface FacturePerteFacade {

     int save(FacturePerte facturePerte);

     List<FacturePerte> findAll();

     FacturePerte findByCode(String code);

     int deleteByCode(String code);

     int updateByCode(FacturePerte update);

     FacturePerte findByMontantHC(double montantHC);

     int deleteByMontantHC(double montantHC);

}
