package com.example.projectsisir.service.impl;

import com.example.projectsisir.bean.Societe;
import com.example.projectsisir.dao.SocieteDao;
import com.example.projectsisir.service.facade.SocieteFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocieteService implements SocieteFacade {
    @Autowired
    SocieteDao societeDao;
    @Autowired
    EmployeService employeService;


    public List<Societe> findAll() {
        return societeDao.findAll();
    }



    public Societe findByIce(String ice) {

        return societeDao.findByIce(ice);
    }

    public Societe findByEmployeCin(String cin) {

        return societeDao.findByEmployeCin(cin);
    }



    public int save(Societe societe) {
        if (findByIce(societe.getIce()) != null) {
            return -1;}
        else if (societe.getIce() == null || societe.getIce().isEmpty()) {
            return -3;}
        else if (societe.getIce().length() > 20) {
            return -2;
        }  else {
            societeDao.save(societe);
            return 1;
        }
    }

    public int updateSociete(Societe societe) {
        Societe oldSociete = societeDao.findByIce(societe.getIce());
        if (oldSociete == null) {
            return -1;
        } else if (societe.getIce() == null || societe.getIce().isEmpty()) {
            return -2;
        } else if (societe.getIce().length() > 20) {
            return -3;
        } else {
            // Copy new values to old object
            oldSociete.setIce(societe.getIce());
            oldSociete.setLibelle(societe.getLibelle());
            oldSociete.setDescription(societe.getDescription());
            oldSociete.setEmploye(societe.getEmploye());
            // Save updated object
            societeDao.save(oldSociete);
            return 1;
        } }
    public List<Societe> findByLibelle(String libelle) {

        return societeDao.findByLibelle( libelle);
    }
    public int deleteByIce(String ice) {
        if (findByIce(ice) == null)
            return -1;
        else {
            societeDao.deleteByIce(ice);
            return 1;
        }
    }

    public int deleteByLibelle(String libelle) {
        if (findByLibelle(libelle) == null)
            return -1;
        else {
            societeDao.deleteByLibelle(libelle);
            return 1;
        }
    }
    public List<Societe> findSocietesWithoutTaxeIS(int year, int trimester) {
        return societeDao.findSocietesWithoutTaxeIS(year, trimester);
    }

}