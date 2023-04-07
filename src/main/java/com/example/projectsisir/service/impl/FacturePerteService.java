package com.example.projectsisir.service.impl;

import com.example.projectsisir.bean.FacturePerte;
import com.example.projectsisir.bean.TaxeIS;
import com.example.projectsisir.dao.FacturePerteDao;
import com.example.projectsisir.dao.TaxeISDao;
import com.example.projectsisir.service.facade.FacturePerteFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturePerteService implements FacturePerteFacade {
    @Autowired
    private FacturePerteDao facturePerteDao;
    @Autowired
    private TaxeISDao taxeISDao;

    public FacturePerte findByCode(String code) {
        return  facturePerteDao.findByCode(code);
    }

    public List<FacturePerte> findAll() {
        return facturePerteDao.findAll();
    }

    public FacturePerte findByMontantHC(double montantHC) {
        return facturePerteDao.findByMontantHC(montantHC);
    }

    public int deleteByMontantHC(double montantHC) {
        return facturePerteDao.deleteByMontantHC(montantHC);
    }

    public int save(FacturePerte facturePerte) {
        TaxeIS taxeIS = new TaxeIS();
        if (taxeIS == null || taxeIS.getSociete() == null || facturePerte == null || facturePerte.getDateFacture() == null) {
            return -1;
        } else if (facturePerte.getMontantTTC() <= 0) {
            return -2;
        } else if (facturePerte.getMontantTTC() > taxeIS.getChiffreAffaire()) {
            return -3;
        } else {
            facturePerte.setTaxeIS(taxeIS);
            facturePerteDao.save(facturePerte);
            double nouveauCharge = taxeIS.getCharge() + facturePerte.getMontantTTC();
            taxeIS.setCharge(nouveauCharge);
            taxeIS.setResultatAvantImpot(taxeIS.getChiffreAffaire() - taxeIS.getCharge());
            taxeIS.setMontantIs(taxeIS.getTauxTaxeIS().getPourcentage() * taxeIS.getResultatAvantImpot());
            taxeIS.setResultatApresImpot(taxeIS.getResultatAvantImpot() - taxeIS.getMontantIs());
            taxeISDao.save(taxeIS);
            return 1;
        }
    }

    public int deleteByCode(String code) {
        facturePerteDao.deleteByCode(code);
        FacturePerte facturePerte = findByCode(code);
        if (facturePerte == null) {
            return -1;
        } else {
            facturePerteDao.delete(facturePerte);
            return 1;
        }
    }

    @Override
    public int updateByCode(FacturePerte update) {
        return 0;
    }


    public int update(FacturePerte facturePerte) {
        FacturePerte existingFacturePerte = findByCode(facturePerte.getCode());
        if (existingFacturePerte == null) {
            return -1;
        } else {
            existingFacturePerte.setMontantHT(facturePerte.getMontantHT());
            existingFacturePerte.setTva(facturePerte.getTva());
            existingFacturePerte.setMontantTTC(facturePerte.getMontantTTC());
            existingFacturePerte.setDateFacture(facturePerte.getDateFacture());
            existingFacturePerte.setTaxeIS(facturePerte.getTaxeIS());
            facturePerteDao.save(existingFacturePerte);
            return 1;
        }
    }

}
