   package com.example.projectsisir.service.impl;

   import com.example.projectsisir.bean.*;
   import com.example.projectsisir.dao.TaxeIRDao;
   import com.example.projectsisir.service.facade.TaxeIRFacade;
   import com.example.projectsisir.service.util.ListUtil;
   import com.example.projectsisir.ws.dto.ResStatDto;
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


      /* public int declareTaxeIR(TaxeIR taxeIR, List<Employe> employes) {
           return save(taxeIR, employes);
       }*/


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
        double totalNet = 0;
        double totalBrut = 0;
        double totalMt = 0;
        TaxeIR existingTaxeIR = taxeIRDao.findByMoisAndAnneeAndSocieteIce(taxeIR.getMois(),taxeIR.getAnnee(),taxeIR.getSociete().getIce());
        if (existingTaxeIR == null) {

            return -1;
        }else {
            existingTaxeIR.setSalaireBrute(totalBrut);
            existingTaxeIR.setSalaireNet(totalNet);
            existingTaxeIR.setMontantIR(totalMt);
            taxeIRDao.save(existingTaxeIR);
            return 1;
        }
    }*/


  /*  public List<TaxeIR> findByCriteria(TaxeIRDto taxeIRDto) {
        String query = "SELECT o FROM TaxeIR o where 1=1 ";
        query += SearchUtil.addConstraint("o", "id", "=", taxeIRDto.getId());
        query += SearchUtil.addConstraint("o", "salaireNet", "LIKE", taxeIRDto.getSalaireNet());
        query += SearchUtil.addConstraint("o", "salaireBrute", "=", taxeIRDto.getSalaireBrute());
        query += SearchUtil.addConstraint("o", "montantIR", "=", taxeIRDto.getMontantIR());
        if (taxeIRDto.getTaxeIREmployesDto() != null) {
            query += SearchUtil.addConstraint("o", "taxeIREmploye.id", "=", taxeIRDto.getTaxeIREmployesDto().getId());
        }
        return entityManager.createQuery(query).getResultList();
    }*/

    public int updateTaxeIR(TaxeIR taxeIR) {

        double totalNet = 0;
        double totalBrut = 0;
        double totalMt = 0;

        TaxeIR existingTaxeIR = taxeIRDao.findByMoisAndAnneeAndSocieteIce(taxeIR.getMois(), taxeIR.getAnnee(), taxeIR.getSociete().getIce());
        if (existingTaxeIR == null) {
            return -1;
        } else {
            Societe loadedSociete = societeService.findByIce(existingTaxeIR.getSociete().getIce());
            existingTaxeIR.setSociete(loadedSociete);
            updateAssociatedLists(taxeIR);
            for (TaxeIREmployes t : taxeIR.getTaxeIREmployes()) {
                Employe employe = employeService.findByCin(t.getEmploye().getCin());
                t.setEmploye(employe);
                t.setMontantIR(t.getTauxTaxeIR().getPourcentage() * t.getSalaireNet());
                t.setSalaireBrute(t.getSalaireNet() - t.getMontantIR());
                taxeIREmployesService.save(t);
                totalNet += t.getSalaireNet();
                totalBrut += t.getSalaireBrute();
                totalMt += t.getMontantIR();
            }
           /* TaxeIREmployes loadedTaxeIr =taxeIREmployesService.findByEmployeCin(taxeIR.getSociete().getEmploye().getCin());*/
           /* existingTaxeIR.setSalaireNet(loadedTaxeIr.getSalaireNet());*/
            existingTaxeIR.setSalaireNet(totalNet);
            existingTaxeIR.setMontantIR(totalMt);
            existingTaxeIR.setSalaireBrute(totalBrut);
            taxeIRDao.save(existingTaxeIR);
            }
            return 1;
        }
    private void updateAssociatedLists(TaxeIR taxeIR) {
        if (taxeIR != null) {
            List<List<TaxeIREmployes>> resultCommandeItems= taxeIREmployesService.getToBeSavedAndToBeDeleted(taxeIREmployesService.findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(taxeIR.getMois(),taxeIR.getAnnee(),taxeIR.getSociete().getIce()),taxeIR.getTaxeIREmployes());
            taxeIREmployesService.delete(resultCommandeItems.get(1));
            associateCommandeItem(taxeIR,resultCommandeItems.get(0));
            taxeIREmployesService.update(resultCommandeItems.get(0));

        }
    }

    private void associateCommandeItem(TaxeIR taxeIR, List<TaxeIREmployes> taxeIREmployes) {
        if (ListUtil.isNotEmpty(taxeIREmployes)) {
            taxeIREmployes.forEach(e -> e.setTaxeIR(taxeIR));
        }
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
