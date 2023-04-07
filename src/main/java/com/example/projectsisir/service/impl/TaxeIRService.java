package com.example.projectsisir.service.impl;



import com.example.projectsisir.bean.*;
import com.example.projectsisir.dao.TaxeIRDao;
import com.example.projectsisir.service.facade.TaxeIRFacade;
import com.example.projectsisir.ws.dto.ResStatDto;
import com.example.projectsisir.ws.dto.TaxeIRDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxeIRService implements TaxeIRFacade {

    @Autowired
    private TaxeIRDao taxeIRDao;

    @Autowired

    private TaxeIREmployesService taxeIREmployesService;
    @Autowired
    private SocieteService societeService;

    @Autowired
    private TauxTaxeIRService tauxTaxeIRService;

    @Autowired
    private EmployeService employeService;


    @Transactional

    public int deleteByMoisAndAnneeAndSocieteIce(int mois, int annee, String ice) {


        int res2 = taxeIREmployesService.deleteByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(mois, annee, ice);
        int res1 = taxeIRDao.deleteByMoisAndAnneeAndSocieteIce(mois, annee, ice);
        return res1 + res2;
    }

    public TaxeIR findByMoisAndAnneeAndSocieteIce(int mois, int annee, String ice) {
        return taxeIRDao.findByMoisAndAnneeAndSocieteIce(mois, annee, ice);
    }

    public List<TaxeIR> findAll() {

        return taxeIRDao.findAll();

    }

    public int save(TaxeIR taxeIR) {

        for (TaxeIREmployes taxeIREmployes : taxeIR.getTaxeIREmployes()) {
            Employe founededemployeIR = employeService.findByCin(taxeIREmployes.getEmploye().getCin());
            if (founededemployeIR == null) {
                return -1;
            }


        }/* && (taxeIR.getSociete().getEmploye().getCin()!=null)*/
        Societe societe = societeService.findByIce(taxeIR.getSociete().getIce());
        taxeIR.setSociete(societe);
        if (taxeIR.getSociete() == null) {
            return -3;
        } else if (
                taxeIRDao.findByMoisAndAnneeAndSocieteIce(taxeIR.getMois(), taxeIR.getAnnee(), taxeIR.getSociete().getIce()) != null) {
            return -4;
        } else {
            double totalNet = 0;
            double totalBrut = 0;
            double totalMt = 0;
            taxeIRDao.save(taxeIR);
            for (TaxeIREmployes taxeIREmploye : taxeIR.getTaxeIREmployes()) {
                Employe employe = employeService.findByCin(taxeIREmploye.getEmploye().getCin());
                taxeIREmploye.setEmploye(employe);
                TauxTaxeIR tauxTaxeIR = tauxTaxeIRService.findBySalaireMaxAndSalaireMin(taxeIREmploye.getSalaireNet());
                taxeIREmploye.setTauxTaxeIR(tauxTaxeIR);
                taxeIREmploye.setMontantIR(tauxTaxeIR.getPourcentage() * taxeIREmploye.getSalaireNet());
                taxeIREmploye.setSalaireBrute(taxeIREmploye.getSalaireNet() - taxeIREmploye.getMontantIR());
                taxeIREmploye.setTaxeIR(taxeIR);
                taxeIREmploye.setSociete(societe);
                totalNet += taxeIREmploye.getSalaireNet();
                totalBrut += taxeIREmploye.getSalaireBrute();
                totalMt += taxeIREmploye.getMontantIR();
                taxeIREmployesService.save(taxeIREmploye);
            }
            taxeIR.setSalaireBrute(totalBrut);
            taxeIR.setSalaireNet(totalNet);
            taxeIR.setMontantIR(totalMt);
            taxeIRDao.save(taxeIR);
            return 1;
        }
    }


   /* public int updateTaxeIR(TaxeIR taxeIR) {
        TaxeIR existingTaxeIR = taxeIRDao.findByMoisAndAnneeAndSocieteIce(taxeIR.getMois(),taxeIR.getAnnee(),taxeIR.getSociete().getIce());
        if (existingTaxeIR == null) {

            return -1;
        }else {
            existingTaxeIR.setSalaireNet(taxeIR.getSalaireNet());
            existingTaxeIR.setDateDeclaration(taxeIR.getDateDeclaration());
            existingTaxeIR.setMontantIR(taxeIR.getMontantIR());
            taxeIRDao.save(existingTaxeIR);
            return 1;
        }
    }*/


    public int updateTaxeIR(TaxeIR taxeIR) {
        TaxeIR existingTaxeIR = taxeIRDao.findByMoisAndAnneeAndSocieteIce(taxeIR.getMois(), taxeIR.getAnnee(), taxeIR.getSociete().getIce());
        if (existingTaxeIR == null) {

            return -1;
        } else {
            existingTaxeIR.setSalaireNet(taxeIR.getSalaireNet());
            existingTaxeIR.setDateDeclaration(taxeIR.getDateDeclaration());
            existingTaxeIR.setMontantIR(taxeIR.getMontantIR());
            existingTaxeIR.setTaxeIREmployes(taxeIR.getTaxeIREmployes());
            taxeIRDao.save(taxeIR);
        }
        return 1;
    }

    public List<ResStatDto> calculStatic(int  annee) {

        List<ResStatDto> res = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            res.add(new ResStatDto(annee, i, taxeIRDao.calculStatic(annee, i)));
        }

        return res;
    }
   /*public int calculerSomme(int mois,int annee ,String ice){
      List  <TaxeIR> taxes = taxeIRDao.findByAnneeAndMoisAndSocieteIce(annee,mois,ice);
        int total =0;
        for (TaxeIR taxe : taxes){
            total += taxe.getMontantIR();
        }
        return total;
    }*/


}
