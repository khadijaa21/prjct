package com.example.projectsisir.service.facade;


import com.example.projectsisir.bean.ISItem;
import com.example.projectsisir.bean.TaxeIS;
import com.example.projectsisir.ws.dto.ResStatiqueISDto;

import java.math.BigDecimal;
import java.util.List;

public interface TaxeIsFacade {

   public TaxeIS findByAnneeAndTrimestreAndSocieteIce(int annee, int trimestre, String ice);
    public TaxeIS findByAnneeAndTrimestre(int annee, int trimestre);

    public int deleteByAnneeAndTrimestre(int annee, int trimestre);

    public int deleteBySocieteIce(String ice);

   public List<TaxeIS> findBySocieteIce(String ice);
    public List<TaxeIS> findAll();

     public int deleteByAnneeAndTrimestreAndSocieteIce(int annee, int trimestre, String ice);

     public int save(ISItem sav);
    public int updateTaxeIS(TaxeIS taxeIS);
    public List<ResStatiqueISDto> calcStatique(int annee);
}
