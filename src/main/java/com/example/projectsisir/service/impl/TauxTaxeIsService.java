package com.example.projectsisir.service.impl;

import com.example.projectsisir.bean.TauxTaxeIS;
import com.example.projectsisir.dao.TauxTaxeIsDao;
import com.example.projectsisir.service.facade.TauxTaxeISFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TauxTaxeIsService implements TauxTaxeISFacade {
    @Autowired
    TauxTaxeIsDao tauxTaxeIsDao;

    @Override
    public TauxTaxeIS findByResultatAvantImpot(double resultatAvantImpot) {
        return tauxTaxeIsDao.findByResultatAvantImpot(resultatAvantImpot);
    }

    public TauxTaxeIS findByDateApplicationDebutAndDateApplicationFin(Date dateDebut, Date dateFin) {
        return tauxTaxeIsDao.findByDateApplicationDebutAndDateApplicationFin(dateDebut, dateFin);
    }

    @Transactional
    public int deleteByDateApplicationDebutAndDateApplicationFin(Date dateDebut, Date dateFin) {
        return tauxTaxeIsDao.deleteByDateApplicationDebutAndDateApplicationFin(dateDebut, dateFin);
    }


    public int save(TauxTaxeIS tauxTaxeIS) {
        //Entre deux dates il ñ'y a qu'un seul taux
        if (findByDateApplicationDebutAndDateApplicationFin(tauxTaxeIS.getDateApplicationDebut(),tauxTaxeIS.getDateApplicationFin())!=null){
            return -1;
            //le pourcentage ne doit pas etre nul et date debut doit etre differente de date fin
        } else if (tauxTaxeIS.getPourcentage()==null||tauxTaxeIS.getDateApplicationDebut()==tauxTaxeIS.getDateApplicationFin()) {
            return -2;
            //le resultat max peut etre null ms resultat min non
        } else if (tauxTaxeIS.getResultatMin()==null) {
            return -3;
        } else {
            tauxTaxeIsDao.save(tauxTaxeIS);
            return 1;
        }
    }
    public List<TauxTaxeIS> findAll() {
        return tauxTaxeIsDao.findAll();
    }

    @Override
    public int update(TauxTaxeIS tauxTaxeIS) {
        TauxTaxeIS tauxTaxeIS1 = findByDateApplicationDebutAndDateApplicationFin(tauxTaxeIS.getDateApplicationDebut(),tauxTaxeIS.getDateApplicationFin());
        if (tauxTaxeIS1 == null) {
            return -1;
        }//Modofier le taux
        tauxTaxeIS1.setPourcentage(tauxTaxeIS.getPourcentage());
        tauxTaxeIS1.setId(tauxTaxeIS.getId());
        tauxTaxeIS1.setDateApplicationFin(tauxTaxeIS.getDateApplicationFin());
        tauxTaxeIS1.setResultMax(tauxTaxeIS.getResultMax());
        tauxTaxeIS1.setResultatMin(tauxTaxeIS.getResultatMin());
        tauxTaxeIsDao.save(tauxTaxeIS1);
        return 1;
    }

    @Override
    public int delete(TauxTaxeIS tauxTaxeIS) {
        if (tauxTaxeIS == null) {
            return -1;
        } else {
            tauxTaxeIsDao.delete(tauxTaxeIS);
            return 1;
        }
    }


    }