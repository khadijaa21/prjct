package com.example.projectsisir.service.impl;
import com.example.projectsisir.bean.*;
import com.example.projectsisir.dao.TaxeIREmployesDao;
import com.example.projectsisir.service.facade.TaxeIREmployesFacade;
import com.example.projectsisir.service.util.ListUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxeIREmployesService implements TaxeIREmployesFacade {
    @Autowired
    TaxeIREmployesDao taxeIREmployesDao;


    @Autowired
    private TauxTaxeIRService tauxTaxeIRService;
    @Autowired
    EmployeService employeService;

    @Autowired
    TaxeIRService taxeIRService;
    @Autowired
    SocieteService societeService;


    public List<List<TaxeIREmployes>> getToBeSavedAndToBeDeleted(List<TaxeIREmployes> oldList, List<TaxeIREmployes> newList) {
        List<List<TaxeIREmployes>> result = new ArrayList<>();
        List<TaxeIREmployes> resultDelete = new ArrayList<>();
        List<TaxeIREmployes> resultUpdateOrSave = new ArrayList<>();
        boolean oldEmpty = ListUtil.isEmpty(oldList);
        boolean newEmpty = ListUtil.isEmpty(newList);
        if (!newEmpty && oldEmpty) {
            resultUpdateOrSave.addAll(newList);
        } else if (newEmpty && !oldEmpty) {
            resultDelete.addAll(oldList);
        } else if (!newEmpty && !oldEmpty) {
            separateToDeleteAndToUpdate(oldList, newList, resultUpdateOrSave, resultDelete);
            populateSaveOrUpdate(oldList, newList, resultUpdateOrSave);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void populateSaveOrUpdate(List<TaxeIREmployes> oldList, List<TaxeIREmployes> newList, List<TaxeIREmployes> resultUpdateOrSave) {
        for (int i = 0; i < newList.size(); i++) {
            TaxeIREmployes myNew = newList.get(i);
            TaxeIREmployes t = oldList.stream().filter(myNew::equals).findFirst().orElse(null);
            if (t == null) {
                resultUpdateOrSave.add(myNew);
            }
        }
    }
    private void separateToDeleteAndToUpdate(List<TaxeIREmployes> oldList, List<TaxeIREmployes> newList, List<TaxeIREmployes> resultUpdateOrSave, List<TaxeIREmployes> resultDelete) {
        for (int i = 0; i < oldList.size(); i++) {
            TaxeIREmployes myOld = oldList.get(i);
            TaxeIREmployes t = newList.stream().filter(myOld::equals).findFirst().orElse(null);
            if (t != null) {
                resultUpdateOrSave.add(t);
            } else {
                resultDelete.add(myOld);
            }
        }
    }

    @Transactional
    public void delete(List<TaxeIREmployes> taxeIREmployes) {
        if (ListUtil.isNotEmpty(taxeIREmployes)) {
            taxeIREmployes.forEach(e -> taxeIREmployesDao.delete(e));
        }
    }

    @Override
    public TaxeIREmployes findByEmployeCin(String cin) {
        return taxeIREmployesDao.findByEmployeCin(cin);
    }

    public void update(List<TaxeIREmployes> taxeIREmployes) {
        if (ListUtil.isNotEmpty(taxeIREmployes)) {
            taxeIREmployes.forEach(e -> {
                Employe loadedEmploye = employeService.findByCin(e.getEmploye().getCin());
                e.setEmploye(loadedEmploye);
                Societe loadedSocieteTaxeIREmploye = societeService.findByIce(e.getSociete().getIce());
                e.setSociete(loadedSocieteTaxeIREmploye);
                TauxTaxeIR loadedTauxTaxeIR = tauxTaxeIRService.findBySalaireMaxAndSalaireMin(e.getSalaireNet());
                e.setTauxTaxeIR(loadedTauxTaxeIR);
                TaxeIR loadedTaxeIR = taxeIRService.findByMoisAndAnneeAndSocieteIce(e.getTaxeIR().getMois(), e.getTaxeIR().getAnnee(), e.getTaxeIR().getSociete().getIce());
                e.setTaxeIR(loadedTaxeIR);
                e.setMontantIR(loadedTauxTaxeIR.getPourcentage()*e.getSalaireNet());
               e.setSalaireBrute(e.getSalaireNet()-e.getMontantIR());
                taxeIREmployesDao.save(e);
            });
        }
    }

    public List<TaxeIREmployes> findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice) {
        return taxeIREmployesDao.findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(mois, annee, ice);
    }

    public int save(TaxeIREmployes taxeIREmploye) {

        if (taxeIREmploye != null && taxeIREmploye.getSociete().getIce() != null && taxeIREmploye.getEmploye().getCin() != null) {
            taxeIREmploye.setEmploye(employeService.findByCin(taxeIREmploye.getEmploye().getCin()));
            taxeIREmploye.setTauxTaxeIR(tauxTaxeIRService.findBySalaireMaxAndSalaireMin(taxeIREmploye.getSalaireNet()));
            taxeIREmploye.setSociete(societeService.findByIce(taxeIREmploye.getSociete().getIce()));
            taxeIREmployesDao.save(taxeIREmploye);

            return 1;
        } else {
            return -1;

        }

    }


    /*public List<TaxeIREmployes> findByCriteria(TaxeIREmployesDto taxeIREmployesDto){

        String query = "SELECT o FROM CommandeItem o where 1=1 ";

        query += SearchUtil.addConstraint( "o", "id","=",taxeIREmployesDto.getId());
        query += SearchUtil.addConstraint( "o", "prix","=",taxeIREmployesDto.getPrix());
        query += SearchUtil.addConstraint( "o", "quantite","=",taxeIREmployesDto.getQuantite());
        query += SearchUtil.addConstraint( "o", "archive","=",taxeIREmployesDto.getArchive());
        query += SearchUtil.addConstraintDate( "o", "dateArchivage","=",taxeIREmployesDto.getDateArchivage());
        query += SearchUtil.addConstraintMinMax("o","prix",taxeIREmployesDto.getPrixMin(),taxeIREmployesDto.getPrixMax());
        query += SearchUtil.addConstraintMinMax("o","quantite",taxeIREmployesDto.getQuantiteMin(),taxeIREmployesDto.getQuantiteMax());
        query += SearchUtil.addConstraintMinMaxDate("o","dateArchivage",taxeIREmployesDto.getDateArchivageMin(),taxeIREmployesDto.getDateArchivageMax());


        if(taxeIREmployesDto.getTaxeIRDto()!=null){
            query += SearchUtil.addConstraint( "o", "commande.id","=",taxeIREmployesDto.getCommandeVo().getId());
            query += SearchUtil.addConstraint( "o", "commande.reference","LIKE",commandeItemVo.getCommandeVo().getReference());
        }

        return entityManager.createQuery(query).getResultList();
    }*/

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

