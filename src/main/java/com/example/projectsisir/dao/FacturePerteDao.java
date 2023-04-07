package com.example.projectsisir.dao;

import com.example.projectsisir.bean.FacturePerte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturePerteDao extends JpaRepository<FacturePerte, Long> {

    FacturePerte findByCode(String code);

    int deleteByCode(String code);

    FacturePerte findByMontantHC(double montantHC);

    int deleteByMontantHC(double montantHC);


}

