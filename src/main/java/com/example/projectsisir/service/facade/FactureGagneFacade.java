package com.example.projectsisir.service.facade;


import com.example.projectsisir.bean.FactureGagne;

import java.util.Date;
import java.util.List;

public interface FactureGagneFacade {

    FactureGagne findById(Long id);

    List<FactureGagne> findAll();

    int deleteByDateFacture(Date dateFacture);

    List<FactureGagne> findBySocieteIceAndDateFactureBetween(String ice, Date startDate, Date endDate);

    int save(FactureGagne factureGagne);

    int deleteById(Long id);

    FactureGagne findByCode(String code);

    int updateByCode(FactureGagne factureGagne);

    int deleteByCode(String code);
}
