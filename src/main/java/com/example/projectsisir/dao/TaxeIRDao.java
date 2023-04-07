package com.example.projectsisir.dao;


import com.example.projectsisir.bean.TaxeIR;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TaxeIRDao extends JpaRepository<TaxeIR, Long> {


    @Transactional
    int deleteByMoisAndAnneeAndSocieteIce(int mois, int annee, String ice);

    List<TaxeIR> findAll();

    TaxeIR findByMoisAndAnneeAndSocieteIce(int mois, int annee, String ice);


    @Query("select sum(t.montantIR)from TaxeIR t where t.mois=:mois And t.annee=:annee")
    BigDecimal calculStatic(@Param("annee") int annee , @Param("mois") int mois );
}






