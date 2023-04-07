package com.example.projectsisir.service.impl;


import com.example.projectsisir.bean.TaxeIREmployes;
import com.example.projectsisir.dao.TaxeIREmployesDao;
import com.example.projectsisir.service.facade.TaxeIREmployesFacade;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxeIREmployesService implements TaxeIREmployesFacade {
    @Autowired
    TaxeIREmployesDao taxeIREmployesDao;


    public List<TaxeIREmployes> findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice) {
        return taxeIREmployesDao.findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce( mois,  annee,  ice);
    }

    public int save(TaxeIREmployes taxeIREmployes) {
        taxeIREmployesDao.save(taxeIREmployes);
        return 1;
    }



    @Transactional
    public int deleteByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice) {
        return taxeIREmployesDao.deleteByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(mois, annee, ice);
    }

    /*public TaxeIREmployes update(TaxeIREmployes taxeIREmployes){
       List <TaxeIREmployes> foundedTaxeIREmploye = findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(taxeIREmployes.getTaxeIR().getMois(),taxeIREmployes.getTaxeIR().getAnnee(),taxeIREmployes.getSociete().getIce());
        if(foundedTaxeIREmploye==null) return null;
        else{
            foundedTaxeIREmploye.setSalaireBrute(taxeIREmployes.getSalaireNet());
            return  taxeIREmployesDao.save(foundedTaxeIREmploye);
        }}


    public void update(List<TaxeIREmployes> taxeIREmployes){
        if(ListUtil.isNotEmpty(taxeIREmployes)){
            taxeIREmployes.forEach(e->taxeIREmployesDao.save(e));
        }*/
    }

