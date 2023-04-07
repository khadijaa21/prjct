package com.example.projectsisir.service.facade;


import com.example.projectsisir.bean.TaxeIR;
import com.example.projectsisir.ws.dto.ResStatDto;

import java.util.List;

public interface TaxeIRFacade {
  public TaxeIR findByMoisAndAnneeAndSocieteIce(int mois, int annee, String ice);

  public int deleteByMoisAndAnneeAndSocieteIce(int mois, int annee, String ice);
  public  int save(TaxeIR taxeIR);

  /*int exec(TaxeIR taxeIR);*/

  /*public  int updateTaxeIR(TaxeIR taxeIR, int mois,int annee, String cin);*/
  public  int updateTaxeIR(TaxeIR taxeIR);

  public List<ResStatDto> calculStatic(int annee);

  public List<TaxeIR> findAll();
}
