package com.example.projectsisir.dao;

import com.example.projectsisir.bean.FactureGagne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FactureGagneDao extends JpaRepository<FactureGagne,Long> {

    FactureGagne findByCode(String code);
    int deleteByCode(String Code);

    FactureGagne findByDateFacture(Date dateFacture);

    int deleteByDateFacture(Date dateFacture);

    List<FactureGagne> findBySocieteIceAndDateFactureBetween(String ice, Date startDate, Date endDate);
}
