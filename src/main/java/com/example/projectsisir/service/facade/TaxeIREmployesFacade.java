package com.example.projectsisir.service.facade;


import com.example.projectsisir.bean.TaxeIREmployes;

import java.util.List;

public interface TaxeIREmployesFacade {

    /*void delete(List<TaxeIREmployes> taxeIREmployes);*/

    void update(List<TaxeIREmployes> commandeItems);


    TaxeIREmployes findByEmployeCin(String cin);

    public List<TaxeIREmployes> findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice);

   int deleteByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice);


}
