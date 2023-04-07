package com.example.projectsisir.service.facade;



import com.example.projectsisir.bean.TaxeIREmployes;

import java.util.List;

public interface TaxeIREmployesFacade {
    List<TaxeIREmployes> findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice);

   int deleteByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice);


}
