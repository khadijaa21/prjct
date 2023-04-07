package com.example.projectsisir.service.impl;

import com.example.projectsisir.bean.FactureGagne;
import com.example.projectsisir.bean.TaxeIS;
import com.example.projectsisir.dao.FactureGagneDao;
import com.example.projectsisir.service.facade.FactureGagneFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class FactureGagneService implements FactureGagneFacade {
    @Autowired
    FactureGagneDao factureGagneDao;
    @Autowired
    SocieteService societeService;
    @Autowired
    TaxeIsService taxeIsService;

    @Override
    public FactureGagne findByCode(String code) {
        return factureGagneDao.findByCode(code);
    }

    @Override
    public int updateByCode(FactureGagne factureGagne) {
        return 0;
    }

@Transactional
    public int deleteByCode(String code) {
        return factureGagneDao.deleteByCode(code);
    }

    public FactureGagne findByDateFacture(Date dateFacture) {
        return factureGagneDao.findByDateFacture(dateFacture);
    }

   @Transactional
    public int deleteByDateFacture(Date dateFacture) {
        return factureGagneDao.deleteByDateFacture(dateFacture);
    }

    @Override
    public List<FactureGagne> findBySocieteIceAndDateFactureBetween(String ice, Date startDate, Date endDate) {
        return factureGagneDao.findBySocieteIceAndDateFactureBetween(ice, startDate, endDate);
    }

    @Override
    public int save(FactureGagne factureGagne)  {
        if (factureGagne.getSociete() == null || factureGagne.getSociete().getIce() == null || societeService.findByIce(factureGagne.getSociete().getIce()) == null) {
            return -1;
        } else {
            double tva = factureGagne.getMontantHT() * factureGagne.getTva() / 100;
            factureGagne.setMontantTTC(factureGagne.getMontantHT() + tva);
            factureGagneDao.save(factureGagne);

            TaxeIS taxeIS = taxeIsService.findByAnneeAndTrimestre(factureGagne.getTaxeIS().getAnnee(), factureGagne.getTaxeIS().getTrimestre());
            taxeIS.setChiffreAffaire(taxeIS.getChiffreAffaire() + factureGagne.getMontantTTC());
            taxeIS.setCharge(taxeIS.getCharge() + factureGagne.getMontantHT());


            return 1;
        }
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    @Override
    public FactureGagne findById(Long id) {
        return factureGagneDao.findById(id).orElse(null);
    }

    @Override
    public List<FactureGagne> findAll() {
        return factureGagneDao.findAll();
    }
}
