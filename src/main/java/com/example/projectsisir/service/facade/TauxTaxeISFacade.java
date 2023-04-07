package com.example.projectsisir.service.facade;

import com.example.projectsisir.bean.TauxTaxeIS;

import java.util.Date;
import java.util.List;

public interface TauxTaxeISFacade {
    TauxTaxeIS findByResultatAvantImpot(double resultatAvantImpot);

    TauxTaxeIS findByDateApplicationDebutAndDateApplicationFin(Date dateDebut, Date dateFin);
    int deleteByDateApplicationDebutAndDateApplicationFin(Date dateDebut, Date dateFin);
    public int save(TauxTaxeIS tauxTaxeIS);
    public List<TauxTaxeIS> findAll();
    public int update(TauxTaxeIS tauxTaxeIS);
    int delete(TauxTaxeIS tauxTaxeIS);
}
