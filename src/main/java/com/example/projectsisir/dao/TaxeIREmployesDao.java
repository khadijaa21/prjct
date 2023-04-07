package com.example.projectsisir.dao;


import com.example.projectsisir.bean.TaxeIREmployes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaxeIREmployesDao extends JpaRepository<TaxeIREmployes,Long> {



    List<TaxeIREmployes> findByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice);

    TaxeIREmployes findByEmployeCin(String cin);

    int deleteByTaxeIRMoisAndTaxeIRAnneeAndSocieteIce(int mois, int annee, String ice);


}
