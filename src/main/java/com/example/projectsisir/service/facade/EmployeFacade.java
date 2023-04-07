package com.example.projectsisir.service.facade;


import com.example.projectsisir.bean.Employe;

import java.util.List;

public interface EmployeFacade {
    public Employe findByCin(String cin) ;
    public List<Employe> findAll();

    boolean existsById(Long id);
    public int deleteByCin(String cin) ;

    public int save(Employe employe) ;
    public Employe findBySocieteIce(String ice);
    Employe findBySalaire(double salaire);
}
