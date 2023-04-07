package com.example.projectsisir.service.impl;

import com.example.projectsisir.bean.Employe;
import com.example.projectsisir.dao.EmployeDao;
import com.example.projectsisir.service.facade.EmployeFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeService implements EmployeFacade {
    @Autowired
    private EmployeDao employeDao;
    @Autowired
    private SocieteService societeService;

    public Employe findByCin(String cin) {
        return employeDao.findByCin(cin);
    }

    public List<Employe> findAll() {
        return employeDao.findAll();
    }

    @Transactional
    public int deleteByCin(String cin) {
        return employeDao.deleteByCin(cin);
    }
    @Override
    public int currentEmployesCount(String ice) {
        var societe = societeService.findByIce(ice);
        if (societe == null) {
            throw new RuntimeException("Societe not found");
        }

        return employeDao.currentEmployesCount(ice);
    }


    public int save(Employe employe) {
        if (findByCin(employe.getCin()) != null) {
            return -1;
        } else if (employe.getSociete() == null || employe.getSociete().getIce() == null
                || societeService.findByIce(employe.getSociete().getIce())==null){
            return -2;
        }else
            employeDao.save(employe);
        return 1;
    }

    @Override
    public Employe findBySocieteIce(String ice) {
        return employeDao.findBySocieteIce(ice);
    }

    @Override
    public Employe findBySalaire(double salaire) {
        return employeDao.findBySalaire(salaire);
    }
}
